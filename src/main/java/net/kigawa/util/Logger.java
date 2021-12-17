package net.kigawa.util;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class Logger {
    private static Logger logger;
    private final java.util.logging.Logger javaLogger;
    private final String Name;

    private Logger(String name, java.util.logging.Logger parentLogger, Level logLevel, File logDir, Handler... handlers) {
        Name = name;

        if (parentLogger != null) name = parentLogger.getName() + "." + name;
        javaLogger = java.util.logging.Logger.getLogger(name);

        javaLogger.setLevel(logLevel);

        if (logDir != null) {
            Calendar calendar = Calendar.getInstance();
            StringBuffer logName = Util.addYearToDate(new StringBuffer(Name));
            File logFile = new File(logDir, Extension.log.addExtension(logName).toString());
            int i = 0;
            while (logFile.exists())
                logFile = new File(Extension.log.addExtension(logName.append("-").append(i)).toString());
            try {
                Handler handler = new FileHandler(logFile.getAbsolutePath());
                javaLogger.addHandler(handler);
                handler.setFormatter(new SimpleFormatter());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        for (Handler handler : handlers) {
            javaLogger.addHandler(handler);
        }
    }

    public static void enable(String name, java.util.logging.Logger parentLogger, Level logLevel, File lodDir, Handler... handlers) {
        logger = new Logger(name, parentLogger, logLevel, lodDir, handlers);
    }

    public static Logger getInstance() {
        if (logger == null) logger = new Logger("logger", null, null, null);
        return logger;
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

    public void fine(Object o) {
        log(o, Level.FINE);
    }

    public void warning(Object o) {
        log(o, Level.WARNING);
    }

    public void severe(Object o) {
        log(o, Level.SEVERE);
    }

    public void info(Object o) {
        log(o, Level.INFO);
    }

    public void config(Object o) {
        log(o, Level.CONFIG);
    }

    public void all(Object o) {
        log(o, Level.ALL);
    }

    public void finer(Object o) {
        log(o, Level.FINER);
    }

    public void finest(Object o) {
        log(o, Level.FINEST);
    }

    public void off(Object o) {
        log(o, Level.OFF);
    }

    public void log(Object o, Level level) {
        if (o instanceof Object[]) {
            for (Object o1 : (Object[]) o) {
                log(o1, level);
            }
            return;
        }
        if (o instanceof Throwable) {
            log(((Throwable) o).getStackTrace(), level);
        }
        javaLogger.log(level, o.toString());
    }

    /**
     * @deprecated
     */
    public void logger(String message) {
        fine(message);
    }
}
