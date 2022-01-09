package net.kigawa.log;

import java.util.logging.Level;

public interface LogSender {
    Logger logger = Logger.getInstance();


    default void fine(Object... o) {
        logger.anSyncLog(o, Level.FINE);
    }

    default String finePass(String str) {
        fine(str);
        return str;
    }

    default void warning(Object... o) {
        logger.anSyncLog(o, Level.WARNING);
    }

    default String warningPass(String str) {
        warning(str);
        return str;
    }

    default void severe(Object... o) {
        logger.anSyncLog(o, Level.SEVERE);
    }

    default String severPass(String str) {
        severe(str);
        return str;
    }

    default void info(Object... o) {
        logger.anSyncLog(o, Level.INFO);
    }

    default String infoPass(String str) {
        info(str);
        return str;
    }

    default void all(Object... o) {
        logger.anSyncLog(o, Level.ALL);
    }

    default String allPass(String str) {
        all(str);
        return str;
    }

    default void finer(Object... o) {
        logger.anSyncLog(o, Level.FINER);
    }

    default String finerPass(String str) {
        fine(str);
        return str;
    }

    default void finest(Object... o) {
        logger.anSyncLog(o, Level.FINEST);
    }

    default String finestPass(String str) {
        fine(str);
        return str;
    }

    default void off(Object... o) {
        logger.anSyncLog(o, Level.OFF);
    }

    default String offPass(String str) {
        off(str);
        return str;
    }

    /**
     * @deprecated
     */
    default void logger(String message) {
        fine(message);
    }
}
