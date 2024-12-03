package core.cache;

import java.util.HashMap;
import java.util.Map;

public class TestCache {
    private static final Map<String, Object> sessionMap = new HashMap<>();

    public static void put(TestCacheKey key, Object value) {
        sessionMap.put(key.getKey(), value);
    }


    public static Object get(TestCacheKey key) {
        return sessionMap.get(key.getKey());
    }

    public static String getStringValue(TestCacheKey key) {
        Object value = sessionMap.get(key.getKey());
        return value != null ? value.toString() : null;
    }

    public static void clear() {
        sessionMap.clear();
    }

    public static boolean containsKey(TestCacheKey key) {
        return sessionMap.containsKey(key.getKey());
    }
}
