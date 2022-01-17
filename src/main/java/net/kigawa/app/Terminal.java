package net.kigawa.app;

import jline.console.ConsoleReader;
import net.kigawa.interfaces.Module;
import net.kigawa.log.LogSender;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

public class Terminal implements LogSender, Module {
    public static final Terminal terminal;
    public static String PREFIX = "]";

    static {
        terminal = new Terminal();
    }

    private final ArrayList<Consumer<String>> consumerList = new ArrayList<>();
    private ConsoleReader reader;
    private BufferedWriter writer;
    private boolean run;

    public Terminal() {
        try {
            reader = new ConsoleReader();
            writer = new BufferedWriter(reader.getOutput());
        } catch (IOException e) {
            warning(e);
        }
    }

    private synchronized void read() {
        while (run) {
            try {
                String line = reader.readLine(PREFIX);
                for (Consumer<String> consumer : consumerList) {
                    consumer.accept(line);
                }
            } catch (IOException e) {
                warning(e);
            }
        }
    }

    @SafeVarargs
    public final void addOnRead(Consumer<String>... consumer) {
        Collections.addAll(consumerList, consumer);
    }

    public BufferedWriter getWriter() {
        return writer;
    }

    @Override
    public void enable() {
        run = true;
        read();
    }

    @Override
    public void disable() {
        run = false;
        notifyAll();
    }
}
