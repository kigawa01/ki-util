package net.kigawa.kutil.kutil.api.function;

/**
 * 3つの引数と返り値のない関数
 * function with three arguments and no return values
 */
@SuppressWarnings("unused")
public interface TriConsumer<T, U, V> {
    void accept(T t, U u, V v);
}
