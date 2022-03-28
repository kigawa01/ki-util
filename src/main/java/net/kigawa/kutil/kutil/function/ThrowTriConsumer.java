package net.kigawa.kutil.kutil.function;


/**
 * 例外を投げれる3つの引数と返り値のない関数
 * function with three arguments and no return values that can throw exception
 */
public interface ThrowTriConsumer<T, U, V> {
    void accept(T t, U u, V v) throws Exception;
}
