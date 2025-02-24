package pageobject;

import core.driver.IWebDriverProvider;
import core.cache.TestCache;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static core.cache.TestCacheKey.PRODUCT_NAME;

public class ProductDetailPage extends AbstractPage {
    @FindBy(xpath = "//span[@id='wishListMainButton']")
    WebElement addToListButton;
    @FindBy(xpath = "//span[@id ='productTitle']")
    WebElement firstProductTitle;

    public ProductDetailPage(IWebDriverProvider driver) {
        super(driver);
        PageFactory.initElements(driver.getWebDriver(), this);
    }
    @Step("Add the product to the shopping list")
    public void addProductToShoppingList() {
        addToListButton.click();
    }
    @Step("Save the first product's name: {productName} to the test cache")
    public void saveFirstProductName() {
        String productName = firstProductTitle.getText();
        TestCache.put(PRODUCT_NAME, productName);
    }
}
