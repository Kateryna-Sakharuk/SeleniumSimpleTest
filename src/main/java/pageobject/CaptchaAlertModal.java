package pageobject;

import core.driver.IWebDriverProvider;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CaptchaAlertModal extends AbstractPage {
    private static final Logger log = LogManager.getLogger(java.sql.DriverManager.class.getSimpleName());
    @FindBy(xpath = "//div[@class='a-box a-alert a-alert-info a-spacing-base']//h4")
    WebElement captcha;

    public CaptchaAlertModal(IWebDriverProvider driver) {
        super(driver);
        PageFactory.initElements(driver.getWebDriver(), this);
    }
    @Step
    public void handleCaptchaIfPresent() {
        try {
            if (captcha.isDisplayed()) {
                WebDriverWait wait = new WebDriverWait(driver.getWebDriver(), Duration.ofSeconds(60));
                WebElement captchaElement = wait.until(ExpectedConditions.visibilityOf(captcha));
                log.info("Captcha detected! Please solve it manually.");
                wait.until(ExpectedConditions.invisibilityOf(captchaElement));
                log.info("Captcha solved, continuing the test.");
            }
        } catch (Exception e) {
            log.error("An unexpected error occurred: " + e.getMessage());
        }
    }
}
