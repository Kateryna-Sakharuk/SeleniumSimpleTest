import core.driver.IWebDriverProvider;
import core.driver.WebDriverProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    IWebDriverProvider driver;

    @BeforeAll
    public void setUp() {
        driver = new WebDriverProvider();
        driver.getWebDriver();
    }

    @AfterAll
    public void tearDown() {
        driver.closeBrowser();
    }
}
