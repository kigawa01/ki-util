package net.kigawa.app;

import jline.console.ConsoleReader;
import net.kigawa.interfaces.Module;
import net.kigawa.log.LogSender;

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
        while (run) {
            System.out.println("read");
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
                warning(e);
            }
        }
    }

    public void write(String str) {
        try {
            consoleReader.accept();
            writer.write(str);
            writer.flush();
            consoleReader.drawLine();
        } catch (IOException e) {
            warning(e);
        }
    }

    @SafeVarargs
    public final void addOnRead(Consumer<String>... consumer) {
        Collections.addAll(consumerList, consumer);
    }

    @Override
    public void enable() {
        if (terminal != null) {
            warning("terminal is already exit!");
            return;
        }

        try {
            if (jline) {
                consoleReader = new ConsoleReader();
                writer = new BufferedWriter(consoleReader.getOutput());
            } else {
                reader = new BufferedReader(new InputStreamReader(System.in));
                writer = new BufferedWriter(new OutputStreamWriter(System.out));
            }
        } catch (IOException e) {
            warning(e);
        }
        terminal = this;

        run = true;
        thread = new Thread(this::read);
        thread.start();
    }

    @Override
    public synchronized void disable() {
        run = false;
        notifyAll();
        thread = null;
        try {
            writer.close();
            writer = null;
            reader.close();
            reader = null;
        } catch (IOException e) {
            warning(e);
        }
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
