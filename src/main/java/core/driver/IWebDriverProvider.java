package core.driver;

import org.openqa.selenium.WebDriver;

//ISP -> these methods apply to one context — WebDriver management.
public interface IWebDriverProvider {
     WebDriver getWebDriver();

    void maximizeWindow();

    void closeBrowser();
}
