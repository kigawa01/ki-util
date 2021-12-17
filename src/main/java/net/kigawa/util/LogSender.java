package net.kigawa.util;

public abstract class LogSender {
    private final Logger logger = Logger.getInstance();

    public void fine(Object o) {
        logger.fine(o);
    }

    public void warning(Object o) {
        logger.warning(o);
    }

    public void severe(Object o) {
        logger.severe(o);
    }

    public void info(Object o) {
        logger.info(o);
    }

    public void config(Object o) {
        logger.config(o);
    }

    public void all(Object o) {
        logger.all(o);
    }

    public void finer(Object o) {
        logger.finer(o);
    }

    public void finest(Object o) {
        logger.finest(o);
    }

    public void off(Object o) {
        logger.off(o);
    }

    /**
     * @deprecated
     */
    public void logger(String message) {
        fine(message);
    }
}
