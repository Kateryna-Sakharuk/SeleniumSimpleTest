package pageobject;

import core.IWebDriverProvider;
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

    public LoginPage(IWebDriverProvider driver) {
        super(driver);
        PageFactory.initElements(driver.getWebDriver(), this);
    }

    public void enterEmail(String userName) {
        emailField.sendKeys(userName);
    }

    public void clickContinueButton() {
        waitUntilElementVisible(continueButton);
        continueButton.click();
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        waitUntilElementVisible(signInSubmitButton);
        signInSubmitButton.click();
    }
}
