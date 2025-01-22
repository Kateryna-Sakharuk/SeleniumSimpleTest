package core.cache;

import lombok.Getter;

public enum TestCacheKey {

    PRODUCT_NAME("PRODUCT_NAME"),
    BROWSER_NAME("BROWSER_NAME"),
    TEST_ENV("TEST_ENV");

    @Getter
    private final String key;

    TestCacheKey(String key) {
        this.key = key;
    }
}
