package core.cache;
import java.util.HashMap;
import java.util.Map;

//SRP - this class is responsible for only one action
public class TestCache {
    private static final Map<String, Object> sessionMap = new HashMap<>();

    public static void put(TestCacheKey key, Object value) {
        sessionMap.put(key.getKey(), value);
    }

    public static Object get(TestCacheKey key) {
        return sessionMap.get(key.getKey());
    }

    public static void clear() {
        sessionMap.clear();
    }

    public static boolean containsKey(TestCacheKey key) {
        return sessionMap.containsKey(key.getKey());
    }
}
