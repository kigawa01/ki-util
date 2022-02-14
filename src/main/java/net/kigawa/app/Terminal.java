package net.kigawa.app;

import jline.console.ConsoleReader;
import net.kigawa.module.Module;
import net.kigawa.log.Formatter;
import net.kigawa.log.LogSender;
import net.kigawa.log.Logger;
import net.kigawa.log.TerminalHandler;
import net.kigawa.thread.ThreadExecutors;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;

public class Terminal implements LogSender {
    public static Terminal terminal;
    public static String PREFIX = "]";

    private final ArrayList<Consumer<String>> consumerList = new ArrayList<>();
    private final boolean jline;
    private ConsoleReader consoleReader;
    private BufferedReader reader;
    private BufferedWriter writer;
    private TerminalHandler terminalHandler;
    private boolean run;
    private Thread thread;

    public Terminal(boolean jline) {
        this.jline = jline;
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
                        warning(e);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void write(String str) {
        try {
            if (!run) {
                System.out.println(str);
                return;
            }
            writer.write( str);
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

    public void onEnable() {
        info("enable terminal");
        if (terminal != null) {
            warning("terminal is already exit!");
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

        terminalHandler = new TerminalHandler(Terminal.terminal, new Formatter());

        Logger.getLogger("").addHandler(terminalHandler);
        for (Handler handler : Logger.getLogger("").getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                Logger.getLogger("").removeHandler(handler);
            }
        }


        run = true;
        ThreadExecutors.execute(this::read);
    }

    public synchronized void onDisable() {
        System.out.println("disable terminal");
        run = false;
        notifyAll();
        thread = null;
        writer = null;
        consoleReader = null;
        reader = null;

        terminal = null;

        java.util.logging.Logger logger = Logger.getLogger("");

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
