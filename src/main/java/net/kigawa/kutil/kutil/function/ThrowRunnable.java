package net.kigawa.kutil.kutil.function;

/**
 * 例外を投げれる関数
 * function that can throw exception
 */
public interface ThrowRunnable {
    void run() throws Exception;
}
