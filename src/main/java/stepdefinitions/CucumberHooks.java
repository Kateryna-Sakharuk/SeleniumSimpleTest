package stepdefinitions;

import core.cache.TestCache;
import core.driver.DefaultWebDriverFactory;
import core.driver.IWebDriverFactory;
import core.driver.IWebDriverProvider;
import core.driver.WebDriverProvider;
import core.properties.PropertyReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static core.cache.TestCacheKey.BROWSER_NAME;
public class CucumberHooks {
    protected static IWebDriverProvider driver;
    private static IWebDriverFactory webDriverFactory;
    public static final Logger logger = LogManager.getLogger("");

    @Before
    public void setUp() {
        logger.info("Initializing WebDriver");

        PropertyReader propertyReader = new PropertyReader();

        if (TestCache.getStringValue(BROWSER_NAME) == null) {
            TestCache.put(BROWSER_NAME, "chrome");
            logger.info("BROWSER_NAME was not set. Defaulting to Chrome.");
        }

        webDriverFactory = new DefaultWebDriverFactory();
        driver = WebDriverProvider.getInstance(webDriverFactory);

        if (driver == null) {
            throw new IllegalStateException("WebDriverProvider returned null WebDriver!");
        }

        driver.getWebDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            logger.info("Closing WebDriver");
            driver.closeBrowser();
        }
    }

    public static IWebDriverProvider getDriver() {
        return driver;
    }
}