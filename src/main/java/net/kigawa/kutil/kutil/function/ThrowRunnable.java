package net.kigawa.kutil.kutil.function;

/**
 * 例外を投げれる引数と返り値のない関数
 * function with no argument and no return values that can throw exception
 */
public interface ThrowRunnable {
    void run() throws Exception;
}
