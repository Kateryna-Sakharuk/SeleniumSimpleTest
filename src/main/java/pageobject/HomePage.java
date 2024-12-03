package pageobject;

import core.driver.IWebDriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static core.properties.Property.BASE_URL;

public class HomePage extends AbstractPage {
    @FindBy(id = "nav-link-accountList")
    WebElement singInButton;
    @FindBy(xpath = "//div[@id='nav-logo']")
    WebElement amazonHeaderLogo;
    @FindBy(xpath = "//div[@role = 'main']")
    WebElement homePageBody;
    @FindBy(xpath = "//span[@id ='nav-link-accountList-nav-line-1']")
    WebElement accountNameElement;
    @FindBy(xpath = "//input[@type ='text']")
    WebElement searchField;
    @FindBy(xpath = "//input[@id ='nav-search-submit-button']")
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
        openWindow(BASE_URL.readProperty());
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
    public void productSearch(String productName){
        waitUntilElementVisible(searchField);
        searchField.click();
        searchField.sendKeys(productName);
        waitUntilElementVisible(searchSubmitButton);
        searchSubmitButton.click();
    }
}
