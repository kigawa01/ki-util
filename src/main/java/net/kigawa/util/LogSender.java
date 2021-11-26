package net.kigawa.util;

public abstract class LogSender {
    public Logger logger;

    public LogSender(Logger logger) {
        this.logger = logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public void info(Object o) {
        if (isNull(o)) return;
        logger.info(o);
    }

    public void waring(Object o) {
        if (isNull(o)) return;
        logger.warning(o);
    }

    public void debug(Object o) {
        if (isNull(o)) return;
        logger.debug(o);
    }

    public void title(Object o) {
        if (isNull(o)) return;
        logger.title(o);
    }

    public boolean isNull(Object o) {
        if (logger != null) return false;
        System.out.println(o);
        return true;
    }
}
