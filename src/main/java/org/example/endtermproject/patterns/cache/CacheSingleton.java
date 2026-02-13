package org.example.endtermproject.patterns.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class CacheSingleton {

    private static volatile CacheSingleton instance;

    private final Map<String, Object> cache = new ConcurrentHashMap<>();

    private CacheSingleton() {}

    public static CacheSingleton getInstance() {
        if (instance == null) {
            synchronized (CacheSingleton.class) {
                if (instance == null) {
                    instance = new CacheSingleton();
                }
            }
        }
        return instance;
    }

    public Object get(String key) {
        return cache.get(key);
    }

    public void put(String key, Object value) {
        cache.put(key, value);
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }
}
