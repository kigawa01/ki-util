package net.kigawa.kutil.kutil.function;

/**
 * 例外を投げれる引数が三つの関数
 * runnable that can throw exception
 */
public interface ThrowTriConsumer<T, U, V> {
    void accept(T t, U u, V v) throws Exception;
}
