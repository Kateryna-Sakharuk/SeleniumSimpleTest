import core.cache.TestCache;
import core.driver.IWebDriverProvider;
import core.driver.WebDriverProvider;
import io.qameta.allure.testng.AllureTestNg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;
import static core.cache.TestCacheKey.BROWSER_NAME;
import static core.cache.TestCacheKey.ENV_NAME;

@Listeners({AllureTestNg.class})
public class BaseTest {
    IWebDriverProvider driver;
    public static final Logger logger = LogManager.getLogger("");
    @BeforeTest
    @Parameters({"browserName", "testEnv"})
    public void setUp(String browserName, String testEnv) {
        TestCache.put(BROWSER_NAME, browserName);
        TestCache.put(ENV_NAME, testEnv);
        driver = new WebDriverProvider();
        driver.getWebDriver();
        ITestContext context = Reporter.getCurrentTestResult().getTestContext();
        context.setAttribute("WebDriver", driver.getWebDriver());
    }
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.closeBrowser();
        }
    }
}