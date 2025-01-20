package core.driver;

import core.cache.TestCache;
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
import static core.cache.TestCacheKey.HUB_URL;

public class WebDriverProvider implements IWebDriverProvider {
    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(WebDriverProvider.class.getSimpleName());

    public WebDriverProvider() {
        log.info("New WebDriverProvider created");
    }

    @Override
    public WebDriver getWebDriver() {
        String browserName = TestCache.getStringValue(BROWSER_NAME);
        String hubUrl = TestCache.getStringValue(HUB_URL);

        if (driver == null) {
            if (hubUrl != null && !hubUrl.isEmpty()) {
                try {
                    log.info("Connecting to Selenium Grid hub at {}", hubUrl);
                    switch (Objects.requireNonNull(browserName).toLowerCase()) {
                        case "chrome" -> {
                            log.info("Chrome driver selected for Selenium Grid");
                            ChromeOptions chromeOptions = new ChromeOptions();
                            driver = new RemoteWebDriver(new URL(hubUrl), chromeOptions);
                        }
                        case "firefox" -> {
                            log.info("Firefox driver selected for Selenium Grid");
                            FirefoxOptions firefoxOptions = new FirefoxOptions();
                            driver = new RemoteWebDriver(new URL(hubUrl), firefoxOptions);
                        }
                        default -> throw new IllegalArgumentException("Unsupported browser: " + browserName);
                    }
                } catch (Exception e) {
                    log.error("Failed to connect to Selenium Grid hub", e);
                    throw new RuntimeException("Failed to connect to Selenium Grid", e);
                }
            } else {
                switch (Objects.requireNonNull(browserName).toLowerCase()) {
                    case "chrome" -> {
                        log.info("Chrome driver selected locally");
                        driver = new ChromeDriver();
                    }
                    case "firefox" -> {
                        log.info("Firefox driver selected locally");
                        driver = new FirefoxDriver();
                    }
                    default -> throw new IllegalArgumentException("Unsupported browser: " + browserName);
                }
            }
        }
        return driver;
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
