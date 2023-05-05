package net.kigawa.kutil.kutil.interfaces;

/**
 * create pass through logger
 */
public interface LoggerInterface {
    default String finePass(String str) {
        fine(str);
        return str;
    }

    void fine(Object... o);

    default String warningPass(String str) {
        warning(str);
        return str;
    }

    void warning(Object... o);

    default String severPass(String str) {
        severe(str);
        return str;
    }

    void severe(Object... o);

    default String infoPass(String str) {
        info(str);
        return str;
    }

    void info(Object... o);

    default String allPass(String str) {
        all(str);
        return str;
    }

    void all(Object... o);

    void finer(Object... o);

    default String finerPass(String str) {
        fine(str);
        return str;
    }

    void finest(Object... o);

    default String finestPass(String str) {
        fine(str);
        return str;
    }

    /**
     * @deprecated
     */
    default void logger(String message) {
        fine(message);
    }
}
