package pageobject;

import core.IWebDriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//a[@id='nav-link-accountList']")
    WebElement accountLink;
    @FindBy(xpath = "//div[@id='a-page']")
    WebElement homePageShow;
    @FindBy(xpath = "//span[@id ='nav-link-accountList-nav-line-1']")
    WebElement accountName;

    public HomePage(IWebDriverProvider driver) {
        super(driver);
        PageFactory.initElements(driver.getWebDriver(), this);
    }

    public void openHomePage(String baseUrl) {
        openWindow(baseUrl);
    }

    public boolean isAccountLinkDisplayed() {
        return waitUntilElementVisible(accountLink);
    }

    public boolean isHomePageDisplayed() {
        return waitUntilElementVisible(homePageShow);
    }

    public boolean isAccountNameDisplayed() {
        return waitUntilElementVisible(accountName);
    }
}
