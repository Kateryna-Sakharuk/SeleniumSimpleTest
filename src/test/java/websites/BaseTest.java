package websites;

import core.cache.TestCache;
import core.driver.IWebDriverProvider;
import core.driver.WebDriverProvider;
import core.properties.PropertyReader;
import io.qameta.allure.testng.AllureTestNg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import static core.cache.TestCacheKey.BROWSER_NAME;
import static core.cache.TestCacheKey.TEST_ENV;

@Listeners({AllureTestNg.class})
public class BaseTest {
    protected IWebDriverProvider driver;
    public static final Logger logger = LogManager.getLogger("");
    protected PropertyReader propertyReader;
    @BeforeClass
    @Parameters({"browserName", "testEnv", "testData"})
    public void setUp( String browserName, String testEnv, String testData) {
        TestCache.put(BROWSER_NAME, browserName);
        TestCache.put(TEST_ENV, testEnv);
        propertyReader = new PropertyReader(testData);
        driver = new WebDriverProvider();
        driver.getWebDriver();
        ITestContext context = Reporter.getCurrentTestResult().getTestContext();
        context.setAttribute("WebDriver", driver.getWebDriver());

    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.closeBrowser();
        }
    }
}