package core.driver;
import core.cache.TestCache;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
            boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920x1080");
            }
            switch (Objects.requireNonNull(browserName).toLowerCase()) {
                case "chrome" -> {
                    log.info("Chrome driver selected");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);
                }
                case "firefox" -> {
                    log.info("Firefox driver selected");
                    WebDriverManager.firefoxdriver().setup();
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
