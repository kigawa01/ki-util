package net.kigawa.kutil.kutil.api.list;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * map that need to create new instance when there is no instance
 *
 * @param <K> key
 * @param <V> value
 */
@SuppressWarnings("unused")
public class GenerateMap<K, V> implements Map<K, V>
{
    private final Map<K, V> map;
    private final Function<K, V> newInstance;

    /**
     * @param newInstance new instance function
     */
    public GenerateMap(Function<K, V> newInstance)
    {
        this(newInstance, null);
    }

    /**
     * @param newInstance new instance function
     * @param map         default values or non values when null
     */
    public GenerateMap(Function<K, V> newInstance, Map<K, V> map)
    {
        this.newInstance = newInstance;
        if (map == null)
            this.map = new HashMap<>();
        else
            this.map = new HashMap<>(map);
    }

    /**
     * @return size
     */
    @Override
    public int size()
    {
        return map.size();
    }

    /**
     * @return return true when empty
     */
    @Override
    public boolean isEmpty()
    {
        return map.isEmpty();
    }

    /**
     * @param key key
     * @return return true when key is contained in keys
     */
    @Override
    public boolean containsKey(Object key)
    {
        return map.containsKey(key);
    }

    /**
     * @param value value
     * @return return true when value is contained in values
     */
    @Override
    public boolean containsValue(Object value)
    {
        return map.containsValue(value);
    }

    /**
     * @param key key
     * @return return value that linked by key
     */
    @Override
    public V get(Object key)
    {
        K safeKey;
        try {
            //noinspection unchecked
            safeKey = (K) key;
        } catch (ClassCastException e) {
            return null;
        }

        var obj = map.get(key);
        if (obj == null) {
            try {
                obj = newInstance.apply(safeKey);
                map.put(safeKey, obj);
            } catch (ClassCastException e) {
                return null;
            }
        }
        return obj;
    }

    /**
     * @param key   key
     * @param value value
     * @return put in the value and link key
     */
    @Override
    public V put(K key, V value)
    {
        return map.put(key, value);
    }

    /**
     * @param key key
     * @return remove by key
     */
    @Override
    public V remove(Object key)
    {
        return map.remove(key);
    }

    /**
     * @param m map that to add
     */
    @Override
    public void putAll(@NotNull Map<? extends K, ? extends V> m)
    {
        map.putAll(m);
    }

    /**
     * clear all
     */
    @Override
    public void clear()
    {
        map.clear();
    }

    /**
     * @return set that from keys
     */
    @NotNull
    @Override
    public Set<K> keySet()
    {
        return map.keySet();
    }

    /**
     * @return collection from values
     */
    @NotNull
    @Override
    public Collection<V> values()
    {
        return map.values();
    }

    /**
     * @return entry set from own
     */
    @NotNull
    @Override
    public Set<Entry<K, V>> entrySet()
    {
        return map.entrySet();
    }
}
