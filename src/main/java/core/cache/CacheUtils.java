package core.cache;

import com.google.common.cache.Cache;
public class CacheUtils {
    private static Cache<String, Object> cache;

    public CacheUtils(Cache<String, Object> cache) {
        this.cache = cache;
    }

    public static String getStringValue(TestCacheKey key) {
        Object value = cache.getIfPresent(key.getKey());
        return value != null ? value.toString() : null;
    }
}