package core.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


//Decorator pattern
public class TestCacheDecorator {
    private static final Logger log = LogManager.getLogger(TestCacheDecorator.class);

    public static void put(TestCacheKey key, Object value) {
        log.info("Putting value: {} with key: {}", value, key.getKey());
        TestCache.put(key, value);
    }

    public static Object get(TestCacheKey key) {
        Object value = TestCache.get(key);
        log.info("Getting value: {} for key: {}", value, key.getKey());
        return value;
    }

    public static String getStringValue(TestCacheKey key) {
        String value = CacheUtils.getStringValue(key);
        log.info("Getting string value: {} for key: {}", value, key.getKey());
        return value;
    }

    public static void clear() {
        log.info("Clearing the cache");
        TestCache.clear();
    }

    public static boolean containsKey(TestCacheKey key) {
        boolean contains = TestCache.containsKey(key);
        log.info("Cache contains key: {}? {}", key.getKey(), contains);
        return contains;
    }
}