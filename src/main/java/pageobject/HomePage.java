package pageobject;

import core.driver.IWebDriverProvider;
import core.properties.PropertyReader;
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

    public HomePage(IWebDriverProvider driver) {
        super(driver);
        PageFactory.initElements(driver.getWebDriver(), this);
    }

    public void clickSingInButton() {
        waitUntilElementVisible(singInButton);
        singInButton.click();
    }

    public void openHomePage() {
        openWindow(PropertyReader.getProperty("base.url"));
    }

    public boolean isAmazonHeaderLogoDisplayed() {
        return amazonHeaderLogo.isDisplayed();
    }

    public boolean isHomePageMainBodyDisplayed() {
        return homePageBody.isDisplayed();
    }

    public boolean isAccountNameContainsText(String accountName) {
        return accountNameElement.getText().contains(accountName);
    }

    public void productSearch(String productName) {
        searchField.click();
        searchField.sendKeys(productName);
        searchSubmitButton.click();
    }
}
