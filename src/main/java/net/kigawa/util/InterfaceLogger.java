package net.kigawa.util;

public interface InterfaceLogger {
    void info(Object o);

    void warning(Object o);

    void title(Object o);

    void debug(Object o);

    /**
     * @deprecated
     */
    void logger(String message);
}
