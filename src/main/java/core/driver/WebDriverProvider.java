package core.driver;

import core.cache.TestCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.DriverManager;
import java.util.Objects;

import static core.cache.TestCacheKey.BROWSER_NAME;

public class WebDriverProvider implements IWebDriverProvider {
    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(DriverManager.class.getSimpleName());
    public WebDriverProvider(){
        log.info("New driver manager created");
    }

    @Override
    public WebDriver getWebDriver() {
        String browserName = TestCache.getStringValue(BROWSER_NAME);
        if (driver == null) {
            switch (Objects.requireNonNull(browserName).toLowerCase()) {
                case "chrome" -> {
                    log.info("Chrome driver selected");
                    driver = new ChromeDriver();
                }
                case "firefox" -> {
                    log.info("Firefox driver selected");
                    driver = new FirefoxDriver();
                }
                default -> throw new IllegalArgumentException("Unsupported browser: " + browserName);
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
