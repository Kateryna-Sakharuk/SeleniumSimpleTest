package pageobject;

import core.driver.IWebDriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {
    @FindBy(xpath = "//input[@type='email']")
    WebElement emailField;
    @FindBy(xpath = "//input[@id='continue']")
    WebElement continueButton;
    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordField;
    @FindBy(xpath = "//input[@id='signInSubmit']")
    WebElement signInSubmitButton;

    CaptchaAlertModal captchaAlertModal;

    public LoginPage(IWebDriverProvider driver) {
        super(driver);
        captchaAlertModal = new CaptchaAlertModal(driver);
        PageFactory.initElements(driver.getWebDriver(), this);
    }

    public void signInWithCredentials(String email, String password) {
        waitUntilElementVisible(emailField);
        emailField.sendKeys(email);
        continueButton.click();
        captchaAlertModal.handleCaptchaIfPresent();
        waitUntilElementVisible(passwordField);
        passwordField.sendKeys(password);
        signInSubmitButton.click();
        captchaAlertModal.handleCaptchaIfPresent();
    }
}
