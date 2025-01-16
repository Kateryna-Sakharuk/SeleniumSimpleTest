package pageobject;
import core.driver.IWebDriverProvider;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {
    protected IWebDriverProvider driver;

    public AbstractPage(IWebDriverProvider driver) {
        this.driver = driver;
    }
    public void openWindow(String url) {
        driver.getWebDriver().get(url);
        driver.maximizeWindow();
    }

    public void waitUntilElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver.getWebDriver(), Duration.ofSeconds(15));
        wait.pollingEvery(Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitUntilPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver.getWebDriver(), Duration.ofSeconds(5));
        try {
            wait.until(webDriver -> {
                String readyState = (String) ((JavascriptExecutor) driver.getWebDriver()).executeScript("return document.readyState");
                return readyState.equals("complete");
            });
        } catch (TimeoutException e) {
            throw new RuntimeException("Page did not load ", e);
        }
    }
}
