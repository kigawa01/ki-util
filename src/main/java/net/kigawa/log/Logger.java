package net.kigawa.log;

import net.kigawa.file.Extension;
import net.kigawa.string.StringUtil;
import net.kigawa.util.TaskStocker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

public class Logger extends java.util.logging.Logger implements LogSender {
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
            StringBuffer logName = StringUtil.addYearToDate(new StringBuffer("log"), "-");
            File logFile = new File(logDirPath.toFile(), Extension.log.addExtension(logName.toString()));
            int i = 0;
            while (logFile.exists()) {
                logFile = new File(logDirPath.toFile(), Extension.log.addExtension(logName + "-" + i));
                i++;
            }

            try {
                logFile.createNewFile();
                FileHandler handler = new FileHandler(logFile.getAbsolutePath());
                handler.setLevel(logLevel);
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
        Path path = null;
        if (logDir != null) path = logDir.toPath();
        logger = new Logger(loggerName, parentLogger, logLevel, path, handlers);
    }

    public static Logger getInstance() {
        if (logger == null) logger = new Logger("logger", null, null, null);
        return logger;
    }

    public static void setLogger(Logger logger) {
        Logger.logger = logger;
    }

    public void removeFileHandler() {
        removeHandler(fileHandler);
        fileHandler = null;
    }

    public void anSyncLog(Log log, Level level) {
        stocker.add(() -> log(log, level));
    }

    @Override
    public void log(Level level, String str) {
        anSyncLog(str, level);
    }

    public  void anSyncLog(Object o, Level level) {
        stocker.add(() -> log(o, level));
    }

    public synchronized void log(Object o, Level level) {
        if (o.getClass().isArray()) {
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

    public synchronized void disable() {
        stocker.end();
    }

    public interface Log {
        @Override
        String toString();
    }
}
