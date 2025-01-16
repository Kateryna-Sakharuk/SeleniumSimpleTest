package pageobject;

import core.driver.IWebDriverProvider;
import core.properties.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {
    @FindBy(xpath = "//a[@data-nav-role = 'signin']")
    WebElement singInButton;
    @FindBy(xpath = "//div[@id = 'nav-logo']")
    WebElement amazonHeaderLogo;
    @FindBy(xpath = "//div[@role = 'main']")
    WebElement homePageBody;
    @FindBy(xpath = " //div[@class = 'nav-line-1-container']//span[contains(@id, 'nav-line')]")
    WebElement accountNameElement;
    @FindBy(xpath = "//input[@placeholder = 'Search Amazon']")
    WebElement searchField;
    @FindBy(xpath = "//input[@id = 'nav-search-submit-button']")
    WebElement searchSubmitButton;

    CaptchaAlertModal captchaAlertModal;

    public HomePage(IWebDriverProvider driver) {
        super(driver);
        captchaAlertModal = new CaptchaAlertModal(driver);
        PageFactory.initElements(driver.getWebDriver(), this);
    }
    @Step("Click on the Sign In button")
    public void clickSingInButton() {
        waitUntilElementVisible(singInButton);
        singInButton.click();
    }
    @Step("Open the home page and handle captcha if present")
    public void openHomePage() {
        openWindow(PropertyReader.getProperty("base.url"));
        captchaAlertModal.handleCaptchaIfPresent();
    }
    @Step("Check if the Amazon header logo is displayed")
    public boolean isAmazonHeaderLogoDisplayed() {
        return amazonHeaderLogo.isDisplayed();
    }
    @Step("Check if the main body of the home page is displayed")
    public boolean isHomePageMainBodyDisplayed() {
        return homePageBody.isDisplayed();
    }
    @Step("Verify if account name contains the expected text: {accountName}")
    public boolean isAccountNameContainsText(String accountName) {
        return accountNameElement.getText().contains(accountName);
    }
    @Step("Search for a product: {productName}")
    public void productSearch(String productName) {
        searchField.click();
        searchField.sendKeys(productName);
        searchSubmitButton.click();
    }
}
