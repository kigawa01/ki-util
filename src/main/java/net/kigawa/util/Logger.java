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
    private final Consumer<Object> consumer;
    private File log;
    private BufferedWriter bw;

    public Logger(boolean log, boolean debug) {
        this(Util.getAbsolutFile(), log, debug, System.out::println);
    }

    public Logger(File dir, boolean log, boolean debug, Consumer<Object> consumer) {
        System.out.println("on logger");
        isLog = log;
        isDebug = debug;
        this.consumer = consumer;

        if (!log) return;
        System.out.println("if log");
        this.log = createLogFile(dir);
        try {
            System.out.println("get reader...");
            bw = new BufferedWriter(new FileWriter(this.log));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File createLogFile(File dir) {
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

    public void warning(Object o){
        log(o,Color.RED,"[WARNING] ");
    }

    public void debug(Object o) {
        log(o, Color.BLUE, "[DEBUG] ");
    }

    public void info(Object o) {
        log(o, Color.white, "[INFO] ");
    }

    public void log(Object o, Color color, String title) {
        StringBuffer sb = new StringBuffer(color.toString()).append(title).append(Color.white);
        sb.append(o);
        log(sb);
    }

    public void log(Object o) {
        StringBuffer sb = new StringBuffer(Util.getTime());
        sb.append(" | ").append(o);
        System.out.println(sb);
        if (isLog) writeLine(o);
    }

    @Override
    public void logger(String message) {
        debug(message);
    }
}
