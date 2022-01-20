package net.kigawa.app;

import jline.console.ConsoleReader;
import net.kigawa.interfaces.Module;
import net.kigawa.log.LogSender;
import net.kigawa.thread.ThreadExecutors;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

public class Terminal implements LogSender, Module {
    public static Terminal terminal;
    public static String PREFIX = "]";

    private final ArrayList<Consumer<String>> consumerList = new ArrayList<>();
    private final boolean jline;
    private ConsoleReader consoleReader;
    private BufferedReader reader;
    private BufferedWriter writer;
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
            if (writer == null) return;
            writer.write("\n" + str);
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

        run = true;
        ThreadExecutors.execute(this::read);
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
