package core.driver;

import org.openqa.selenium.WebDriver;

public interface IWebDriverProvider {
     WebDriver getWebDriver();

    void maximizeWindow();

    void closeBrowser();
}
