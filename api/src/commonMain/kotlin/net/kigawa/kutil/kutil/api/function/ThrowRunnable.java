package net.kigawa.kutil.kutil.api.function;

/**
 * 例外を投げれる引数と返り値のない関数
 * function with no argument and no return values that can throw exception
 */
@SuppressWarnings("unused")
public interface ThrowRunnable {
    void run() throws Exception;
}
