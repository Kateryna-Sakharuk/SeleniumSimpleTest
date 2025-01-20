package core.cache;

import lombok.Getter;

public enum TestCacheKey {

    PRODUCT_NAME("PRODUCT_NAME"),
    BROWSER_NAME("BROWSER_NAME"),
    ENV_NAME("ENV_NAME"),
    HUB_URL("HUB_URL");

    @Getter
    private final String key;

    TestCacheKey(String key) {
        this.key = key;
    }
}
