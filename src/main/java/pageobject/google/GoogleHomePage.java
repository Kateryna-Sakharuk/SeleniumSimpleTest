package pageobject.google;

import core.driver.IWebDriverProvider;
import core.properties.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobject.AbstractPage;

public class GoogleHomePage extends AbstractPage {

    public GoogleHomePage(IWebDriverProvider driver) {
        super(driver);
        PageFactory.initElements(driver.getWebDriver(), this);
    }

    @FindBy(xpath = "//textarea[@title = 'Пошук']")
    WebElement searchField;

    @FindBy(xpath = "(//div[@role = 'option'])[1]")
    WebElement firstRandomResult;
    @Step("Open the home page")
    public void openHomePage() {
        openWindow(PropertyReader.getProperty("base.url"));
    }
    @Step("Search for a product: {productName}")
    public void productSearch(String productName) {
        searchField.sendKeys(productName);
        waitUntilElementVisible(firstRandomResult);
        firstRandomResult.click();
    }
}
