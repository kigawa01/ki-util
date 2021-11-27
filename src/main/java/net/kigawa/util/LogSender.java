package net.kigawa.util;

public abstract class LogSender implements InterfaceLogger {
    public InterfaceLogger logger;

    public LogSender(InterfaceLogger logger) {
        this.logger = logger;
    }

    public void setLogger(InterfaceLogger logger) {
        this.logger = logger;
    }

    @Override
    public void info(Object o) {
        if (isNull(o)) return;
        logger.info(o);
    }

    @Override
    public void warning(Object o) {
        if (isNull(o)) return;
        logger.warning(o);
    }

    @Override
    public void debug(Object o) {
        if (isNull(o)) return;
        logger.debug(o);
    }

    @Override
    public void title(Object o) {
        if (isNull(o)) return;
        logger.title(o);
    }

    public boolean isNull(Object o) {
        if (logger != null) return false;
        System.out.println(o);
        return true;
    }

    /**
     * @deprecated
     */
    @Override
    public void logger(String message) {
        debug(message);
    }
}
