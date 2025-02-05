package core.driver;

import core.cache.TestCacheDecorator;
import core.properties.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static core.cache.TestCacheKey.BROWSER_NAME;
import static core.cache.TestCacheKey.TEST_ENV;

public class WebDriverProvider implements IWebDriverProvider {
    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(WebDriverProvider.class.getSimpleName());
    private static WebDriverProvider instance;

    private final IWebDriverFactory webDriverFactory;

    private WebDriverProvider(IWebDriverFactory webDriverFactory) {
        this.webDriverFactory = webDriverFactory;
        log.info("New WebDriverProvider created");
    }


    //Singleton pattern
    public static WebDriverProvider getInstance(IWebDriverFactory webDriverFactory) {
        if (instance == null) {
            synchronized (WebDriverProvider.class) {
                if (instance == null) {
                    instance = new WebDriverProvider(webDriverFactory);
                }
            }
        }
        return instance;
    }

    @Override
    public void maximizeWindow() {
        log.info("Logging: Maximizing window...");
        if (driver != null) {
            driver.manage().window().maximize();
        }
    }

    @Override
    public void closeBrowser() {
        log.info("Logging: Closing browser...");
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Override
    public WebDriver getWebDriver() {
        if (driver == null) {
            String testEnv = TestCacheDecorator.getStringValue(TEST_ENV);
            driver = "remote".equalsIgnoreCase(testEnv) ? getRemoteEnv() : getLocalEnv();
        }
        return driver;
    }

    private WebDriver getRemoteEnv() {
        String browserName = TestCacheDecorator.getStringValue(BROWSER_NAME);
        String hubUrl = PropertyReader.getProperty("hub.url").toLowerCase();
        return webDriverFactory.createWebDriver(browserName, true, hubUrl);
    }

    private WebDriver getLocalEnv() {
        String browserName = TestCacheDecorator.getStringValue(BROWSER_NAME);
        return webDriverFactory.createWebDriver(browserName, false);
    }
}
