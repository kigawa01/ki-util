package net.kigawa.kutil.kutil.list;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class GenerateMap<K, V> implements Map<K, V> {
    private final Map<K, V> map = new HashMap<>();
    private final Function<K, V> newInstance;

    public GenerateMap(Function<K, V> newInstance) {
        this.newInstance = newInstance;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        var obj = map.get(key);
        if (obj == null) {
            try {
                obj = newInstance.apply((K) key);
                map.put((K) key, obj);
            } catch (ClassCastException e) {
                return null;
            }
        }
        return obj;
    }

    @Override
    public V put(K key, V value) {
        return map.put(key, value);
    }

    @Override
    public V remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }
}
