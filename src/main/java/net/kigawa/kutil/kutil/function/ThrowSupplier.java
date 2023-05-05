package net.kigawa.kutil.kutil.function;

/**
 * 結果のサプライヤを表します
 * Represents a supplier of results.
 */
public interface ThrowSupplier<T>
{
    T get() throws Exception;
}
