package pageobject;

import core.IWebDriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {
    IWebDriverProvider driver;

    public AbstractPage(IWebDriverProvider driver) {
        this.driver = driver;
    }

    public void openWindow(String url) {
        driver.getWebDriver().get(url);
        driver.maximizeWindow();
    }

    public boolean waitUntilElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver.getWebDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }
    public boolean isCurrentUrlContains(String expectedUrl) {
        String currentUrl = driver.getWebDriver().getCurrentUrl();
        return currentUrl.contains(expectedUrl);
    }

}
