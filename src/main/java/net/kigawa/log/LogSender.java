package net.kigawa.log;

public interface LogSender {
    Logger logger = Logger.getInstance();

    default void fine(Object o) {
        logger.fine(o);
    }

    default void warning(Object o) {
        logger.warning(o);
    }

    default void severe(Object o) {
        logger.severe(o);
    }

    default void info(Object o) {
        logger.info(o);
    }

    default void config(Object o) {
        logger.config(o);
    }

    default void all(Object o) {
        logger.all(o);
    }

    default void finer(Object o) {
        logger.finer(o);
    }

    default void finest(Object o) {
        logger.finest(o);
    }

    default void off(Object o) {
        logger.off(o);
    }

    /**
     * @deprecated
     */
    default void logger(String message) {
        fine(message);
    }
}
