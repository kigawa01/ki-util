package net.kigawa.kutil.kutil.app;

import jline.console.ConsoleReader;
import net.kigawa.kutil.kutil.interfaces.Logger;
import net.kigawa.kutil.kutil.interfaces.Module;
import net.kigawa.kutil.kutil.thread.ThreadExecutors;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;

public class Terminal implements Module {
    public static Terminal terminal;
    public static String PREFIX = "]";

    private final ArrayList<Consumer<String>> consumerList = new ArrayList<>();
    private final boolean jline;
    private Logger logger;
    private ConsoleReader consoleReader;
    private BufferedReader reader;
    private BufferedWriter writer;
    private TerminalHandler terminalHandler;
    private boolean run;
    private Thread thread;

    public Terminal(boolean jline, Logger logger) {
        this.logger = logger;
        this.jline = jline;
    }

    public synchronized void write(String str) {
        try {
            if (!run) {
                System.out.println(str);
                return;
            }
            writer.write(str);
            writer.flush();
            consoleReader.drawLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SafeVarargs
    public final void addOnRead(Consumer<String>... consumer) {
        Collections.addAll(consumerList, consumer);
    }

    @Override
    public void enable() {
        logger.info("enable terminal");
        if (terminal != null) {
            logger.warning("terminal is already exit!");
            return;
        }

        try {
            if (jline) {
                consoleReader = new ConsoleReader(System.in, System.out);
                writer = new BufferedWriter(consoleReader.getOutput());
            } else {
                reader = new BufferedReader(new InputStreamReader(System.in));
                writer = new BufferedWriter(new OutputStreamWriter(System.out));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        terminal = this;

        terminalHandler = new TerminalHandler(Terminal.terminal, new Formatter(), logger);

        java.util.logging.Logger.getLogger("").addHandler(terminalHandler);
        for (Handler handler : java.util.logging.Logger.getLogger("").getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                java.util.logging.Logger.getLogger("").removeHandler(handler);
            }
        }


        run = true;
        ThreadExecutors.execute(this::read);
    }

    private void read() {
        while (true) {
            synchronized (this) {
                if (!run) return;
            }
            try {
                String line;
                if (jline) line = consoleReader.readLine(PREFIX, null);
                else line = reader.readLine();

                for (Consumer<String> consumer : consumerList) {
                    try {
                        consumer.accept(line);
                    } catch (Exception e) {
                        logger.warning(e);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void disable() {
        System.out.println("disable terminal");
        run = false;
        notifyAll();
        thread = null;
        writer = null;
        consoleReader = null;
        reader = null;

        terminal = null;

        java.util.logging.Logger logger = java.util.logging.Logger.getLogger("");

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new Formatter());
        logger.addHandler(consoleHandler);
        logger.removeHandler(terminalHandler);


        terminalHandler = null;
    }

    public BufferedWriter getWriter() {
        return writer;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public ConsoleReader getConsoleReader() {
        return consoleReader;
    }
}
