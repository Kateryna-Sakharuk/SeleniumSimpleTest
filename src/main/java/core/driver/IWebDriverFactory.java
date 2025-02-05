package core.driver;

import org.openqa.selenium.WebDriver;


//DIP -> now webDriver creation can be another when for example
//SpecificWebDriverFactory will implement createWebDriver() method from this interface
//ISP
public interface IWebDriverFactory {
    WebDriver createWebDriver(String browserName, boolean isRemote, String... hubUrl);
}
