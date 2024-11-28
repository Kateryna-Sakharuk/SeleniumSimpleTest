package pageobject;

import core.IWebDriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static core.Property.BASE_URL;

public class HomePage extends AbstractPage {
    @FindBy(id = "nav-link-accountList")
    WebElement singInButton;
    @FindBy(xpath = "//div[@id='nav-logo']")
    WebElement amazonHeaderLogo;
    @FindBy(xpath = "//div[@role = 'main']")
    WebElement homePageBody;
    @FindBy(xpath = "//span[@id ='nav-link-accountList-nav-line-1']")
    WebElement accountNameElement;

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
}
