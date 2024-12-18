package core.cache;

import lombok.Getter;

public enum TestCacheKey {

    PRODUCT_NAME("PRODUCT_NAME");

    @Getter
    private final String key;

    TestCacheKey(String key) {
        this.key = key;
    }
}
