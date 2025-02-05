package core.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.util.Objects;

//Factory pattern -> creates objects of different types based on some conditions.
// User passes the browser name and other parameters, and the factory method decides which object to create.
//SRP -> is only responsible for creating a WebDriver instance depending on the parameters passed.
//OCP -> WebDriverProvider open for extension in DefaultWebDriverFactory, and closed for modification in itself

public class DefaultWebDriverFactory implements IWebDriverFactory{
    private static final Logger log = LogManager.getLogger(DefaultWebDriverFactory.class.getSimpleName());
    @Override
    public WebDriver createWebDriver(String browserName, boolean isRemote, String... hubUrl) {
        browserName = Objects.requireNonNull(browserName).toLowerCase();
        try {
            switch (browserName) {
                case "chrome":
                    log.info("{} driver selected", isRemote ? "Chrome (Remote)" : "Chrome");
                    return isRemote ? new RemoteWebDriver(
                            URI.create(hubUrl[0]).toURL(), new ChromeOptions()) : new ChromeDriver();
                case "firefox":
                    log.info("{} driver selected", isRemote ? "Firefox (Remote)" : "Firefox");
                    return isRemote ? new RemoteWebDriver( URI.create(hubUrl[0]).toURL(), new FirefoxOptions()) : new FirefoxDriver();
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browserName);
            }
        } catch (Exception e) {
            log.error("Error creating WebDriver", e);
            throw new RuntimeException("Failed to create WebDriver", e);
        }
    }
}
