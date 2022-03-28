package net.kigawa.kutil.kutil.interfaces;

/**
 * class that need disable process
 */
public interface Module {
    /**
     * to start process
     */
    void enable();

    /**
     * to end process
     */
    void disable();
}
