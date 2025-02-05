package websites;

import core.cache.TestCacheDecorator;
import core.driver.DefaultWebDriverFactory;
import core.driver.IWebDriverFactory;
import core.driver.IWebDriverProvider;
import core.driver.WebDriverProvider;
import core.properties.PropertyReader;
import io.qameta.allure.testng.AllureTestNg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static core.cache.TestCacheKey.BROWSER_NAME;
import static core.cache.TestCacheKey.TEST_ENV;

@Listeners({AllureTestNg.class})
public class BaseTest {
    protected IWebDriverProvider driver;
    public static final Logger logger = LogManager.getLogger("");
    protected PropertyReader propertyReader;
    private IWebDriverFactory webDriverFactory;

    @BeforeClass
    @Parameters({"browserName", "testEnv", "testData"})
    public void setUp(@Optional("chrome") String browserName, @Optional("local") String testEnv, @Optional("amazonTestData.properties") String testData) {
        TestCacheDecorator.put(BROWSER_NAME, browserName);
        TestCacheDecorator.put(TEST_ENV, testEnv);

        propertyReader = new PropertyReader(testData);
        webDriverFactory = new DefaultWebDriverFactory(); // Initialize explicitly.
        driver = WebDriverProvider.getInstance(webDriverFactory); // Pass factory to provider.
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