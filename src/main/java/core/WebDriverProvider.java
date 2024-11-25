package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverProvider implements IWebDriverProvider {
    public static WebDriver driver = new ChromeDriver();

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }

    @Override
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    @Override
    public void closeBrowser() {
        driver.quit();
    }
}
