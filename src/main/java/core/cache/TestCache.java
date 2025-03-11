package core.cache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

//SRP - this class is responsible for only one action

public class TestCache {
    private static final Map<String, Object> sessionMap = new HashMap<>();
    private static final Logger log = LogManager.getLogger(TestCache.class);

    public static void put(TestCacheKey key, Object value) {
        log.info("Putting value: {} with key: {}", value, key.getKey());
        sessionMap.put(key.getKey(), value);
    }

    public static Object get(TestCacheKey key) {
        log.info("Getting value:  for key: {}", key.getKey());
        return sessionMap.get(key.getKey());
    }

    public static String getStringValue(TestCacheKey key) {
        Object value = sessionMap.get(key.getKey());
        return value != null ? value.toString() : null;
    }

    public static void clear() {
        log.info("Clearing the cache");
        sessionMap.clear();
    }

    public static boolean containsKey(TestCacheKey key) {
        log.info("Cache contains key: {}", key.getKey());
        return sessionMap.containsKey(key.getKey());
    }
}
