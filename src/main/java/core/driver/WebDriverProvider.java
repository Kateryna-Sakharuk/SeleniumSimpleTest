package core.driver;

import core.cache.TestCache;
import core.properties.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Objects;

import static core.cache.TestCacheKey.BROWSER_NAME;
import static core.cache.TestCacheKey.TEST_ENV;

public class WebDriverProvider implements IWebDriverProvider {
    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(WebDriverProvider.class.getSimpleName());

    public WebDriverProvider() {
        log.info("New WebDriverProvider created");
    }

    @Override
    public WebDriver getWebDriver() {
        if (driver == null) {
            String testEnv = TestCache.getStringValue(TEST_ENV);
            driver = "remote".equalsIgnoreCase(testEnv) ? getRemoteEnv() : getLocalEnv();
        }
        return driver;
    }

    private WebDriver createWebDriver(String browserName, boolean isRemote, String... hubUrl) {
        browserName = Objects.requireNonNull(browserName).toLowerCase();
        try {
            switch (browserName) {
                case "chrome":
                    log.info("{} driver selected", isRemote ? "Chrome (Remote)" : "Chrome");
                    return isRemote ? new RemoteWebDriver(new URL(hubUrl[0]), new ChromeOptions()) : new ChromeDriver();
                case "firefox":
                    log.info("{} driver selected", isRemote ? "Firefox (Remote)" : "Firefox");
                    return isRemote ? new RemoteWebDriver(new URL(hubUrl[0]), new FirefoxOptions()) : new FirefoxDriver();
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browserName);
            }
        } catch (Exception e) {
            log.error("Error creating WebDriver", e);
            throw new RuntimeException("Failed to create WebDriver", e);
        }
    }

    private WebDriver getLocalEnv() {
        String browserName = TestCache.getStringValue(BROWSER_NAME);
        return createWebDriver(browserName, false);
    }

    private WebDriver getRemoteEnv() {
        String browserName = TestCache.getStringValue(BROWSER_NAME);
        String hubUrl = PropertyReader.getProperty("hub.url").toLowerCase();
        return createWebDriver(browserName, true, hubUrl);
    }


    @Override
    public void maximizeWindow() {
        if (driver != null) {
            driver.manage().window().maximize();
        }
    }

    @Override
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
