package net.kigawa.log;

import net.kigawa.file.Extension;
import net.kigawa.util.TaskStocker;
import net.kigawa.util.Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

public class Logger extends java.util.logging.Logger {
    private static Logger logger;
    private final TaskStocker stocker = new TaskStocker();
    private FileHandler fileHandler;

    protected Logger(String name, java.util.logging.Logger parentLogger, Level logLevel, Path logDirPath, Handler... handlers) {
        super(name, null);
        if (parentLogger == null) setParent(Logger.getLogger(""));
        else setParent(parentLogger);

        setLevel(logLevel);

        if (logDirPath != null) {
            logDirPath.toFile().mkdirs();
            Calendar calendar = Calendar.getInstance();
            StringBuffer logName = Util.addYearToDate(new StringBuffer("log"), "-");
            File logFile = new File(logDirPath.toFile(), Extension.log.addExtension(logName.toString()));
            int i = 0;
            while (logFile.exists()) {
                logFile = new File(logDirPath.toFile(), Extension.log.addExtension(logName + "-" + i));
                i++;
            }

            try {
                logFile.createNewFile();
                FileHandler handler = new FileHandler(logFile.getAbsolutePath());
                addHandler(handler);
                handler.setFormatter(new Formatter());
                fileHandler = handler;
            } catch (IOException e) {
                Logger.getInstance().warning(e);
            }

        }

        for (Handler handler : handlers) {
            addHandler(handler);
        }
    }

    public static void enable(String name, java.util.logging.Logger parentLogger, Level logLevel, File logDir, Handler... handlers) {
        String loggerName = name;
        if (parentLogger != null) loggerName = parentLogger.getName() + "." + name;
        logger = new Logger(loggerName, parentLogger, logLevel, logDir.toPath(), handlers);
    }

    public static Logger getInstance() {
        if (logger == null) logger = new Logger("logger", null, null, null);
        return logger;
    }

    public void removeFileHandler() {
        removeHandler(fileHandler);
        fileHandler = null;
    }

    public void fine(Object o) {
        anSyncLog(o, Level.FINE);
    }

    public void warning(Object o) {
        anSyncLog(o, Level.WARNING);
    }

    public void severe(Object o) {
        anSyncLog(o, Level.SEVERE);
    }

    public void info(Object o) {
        anSyncLog(o, Level.INFO);
    }

    public void config(Object o) {
        anSyncLog(o, Level.CONFIG);
    }

    public void all(Object o) {
        anSyncLog(o, Level.ALL);
    }

    public void finer(Object o) {
        anSyncLog(o, Level.FINER);
    }

    public void finest(Object o) {
        anSyncLog(o, Level.FINEST);
    }

    public void off(Object o) {
        anSyncLog(o, Level.OFF);
    }

    public synchronized void anSyncLog(Object o, Level level) {
        stocker.add(() -> log(o, level));
    }

    public synchronized void log(Object o, Level level) {
        if (o.getClass().isArray()) {
            log(o.toString(), level);
            for (Object o1 : (Object[]) o) {
                log(o1, level);
            }
            return;
        }
        if (o instanceof Throwable) {
            Throwable throwable = (Throwable) o;
            log(throwable.toString(), level);
            log(throwable.getStackTrace(), level);
            log(throwable.getSuppressed(), level);
            return;
        }
        if (o instanceof StackTraceElement) {
            StackTraceElement element = (StackTraceElement) o;
            log("\tat " + element, level);
            return;
        }
        super.log(level, o.toString());
    }

    /**
     * @deprecated
     */
    public void logger(String message) {
        fine(message);
    }
}
