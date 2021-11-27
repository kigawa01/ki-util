package net.kigawa.util;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;

public class Logger implements InterfaceLogger {
    private final boolean isLog;
    private final boolean isDebug;
    private final boolean timestamp;
    private final Consumer<String> consumer;
    private BufferedWriter bw;

    public Logger(boolean log, boolean debug) {
        this(Util.getAbsolutFile(), log, debug, System.out::println, true);
    }

    public Logger(File dir, boolean log, boolean debug, Consumer<String> consumer, boolean timestamp) {
        System.out.println("on logger");
        isLog = log;
        isDebug = debug;
        this.timestamp = timestamp;
        this.consumer = consumer;

        if (!log) return;
        System.out.println("if log");
        File logFile = createLogFile(dir);
        try {
            System.out.println("get reader...");
            bw = new BufferedWriter(new FileWriter(logFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File createLogFile(File dir) {
        dir.mkdirs();
        int num = -1;
        File log;
        do {
            StringBuffer name = new StringBuffer(Util.getDate());
            if (num >= 0) name.append("-").append(num);
            log = new File(dir, name.toString());
            num++;
        } while (log.exists());
        return log;
    }

    public void writeLine(Object o) {
        try {
            bw.write(o.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void title(Object o) {
        logArray(o, Color.GREEN, "[TITLE] ", Color.GREEN);
    }

    public void warning(Object o) {
        logArray(o, Color.RED, "[WARNING] ", Color.RED);
    }

    public void debug(Object o) {
        if (!isDebug) return;
        logArray(o, Color.BLUE, "[DEBUG] ", Color.white);
    }

    public void info(Object o) {
        logArray(o, Color.white, "[INFO] ", Color.white);
    }

    public void logArray(Object o, Color color, String title, Color messageColor) {
        Util.execLog(o, (String s) -> log(s, color, title, messageColor));
    }

    public void log(Object o, Color color, String title, Color messageColor) {
        StringBuffer sb = new StringBuffer(color.toString()).append(title).append(messageColor);
        sb.append(o);
        log(sb);
    }

    public void log(Object o) {
        StringBuffer sb = new StringBuffer();
        if (timestamp) sb.append(Util.getTime());
        sb.append(" | ").append(o);
        consumer.accept(sb.toString());
        if (isLog) writeLine(o);
    }

    /**
     * @deprecated
     */
    public void logger(String message) {
        debug(message);
    }
}
