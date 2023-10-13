package net.kigawa.kutil.kutil.api.function;

/**
 * 結果のサプライヤを表します
 * Represents a supplier of results.
 */
@SuppressWarnings("unused")
public interface ThrowSupplier<T>
{
    T get() throws Exception;
}
