package pageobject;

import core.IWebDriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static core.Property.BASE_URL;

public class HomePage extends AbstractPage {
    @FindBy(id = "nav-link-accountList")
    WebElement singIn;
    @FindBy(xpath = "//div[@id='nav-logo']")
    WebElement logo;
    @FindBy(xpath = "//div[@role = 'main']")
    WebElement homePage;
    @FindBy(xpath = "//span[@id ='nav-link-accountList-nav-line-1']")
    WebElement accountNameElement;

    public HomePage(IWebDriverProvider driver) {
        super(driver);
        PageFactory.initElements(driver.getWebDriver(), this);
    }

    public void singIn() {
        waitUntilElementVisible(singIn);
        singIn.click();
    }

    public void openHomePage() {
        openWindow(BASE_URL.readProperty());
        isLogoDisplayed();
    }

    public boolean isLogoDisplayed() {
        return waitUntilElementVisible(logo);
    }

    public boolean isHomePageMainElementDisplayed() {
        return homePage.isDisplayed();
    }

    public boolean isAccountNameContainsText(String accountName) {
        return accountNameElement.getText().contains(accountName);
    }
}
