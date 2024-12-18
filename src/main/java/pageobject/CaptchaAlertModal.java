package pageobject;

import core.driver.IWebDriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CaptchaAlertModal extends AbstractPage {

    @FindBy(xpath = "//div[@class='a-box a-alert a-alert-info a-spacing-base']//h4")
    WebElement captcha;

    public CaptchaAlertModal(IWebDriverProvider driver) {
        super(driver);
        PageFactory.initElements(driver.getWebDriver(), this);
    }

    public void handleCaptchaIfPresent() {
        try {
            if (captcha.isDisplayed()) {
                WebDriverWait wait = new WebDriverWait(driver.getWebDriver(), Duration.ofSeconds(60));
                WebElement captchaElement = wait.until(ExpectedConditions.visibilityOf(captcha));
                System.out.println("Captcha detected! Please solve it manually.");
                wait.until(ExpectedConditions.invisibilityOf(captchaElement));
                System.out.println("Captcha solved, continuing the test.");
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
