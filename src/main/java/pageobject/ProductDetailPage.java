package pageobject;

import core.driver.IWebDriverProvider;
import core.cache.TestCache;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static core.cache.TestCacheKey.PRODUCT_NAME;

public class ProductDetailPage extends AbstractPage {
    @FindBy(xpath = "//input[@id ='add-to-wishlist-button-submit']")
    WebElement addToListButton;
    @FindBy(xpath = "//a[contains(text(), 'View Your List')]")
    WebElement viewYourListButton;
    @FindBy(xpath = "//span[@id ='productTitle']")
    WebElement firstProductTitle;


    public ProductDetailPage(IWebDriverProvider driver) {
        super(driver);
        PageFactory.initElements(driver.getWebDriver(), this);
    }

    public void addProductToShoppingList() {
        waitUntilElementVisible(addToListButton);
        addToListButton.click();
    }

    public void clickYourListButton() {
        waitUntilElementVisible(viewYourListButton);
        viewYourListButton.click();
    }

    public void saveFirstProductName() {
        String productName = firstProductTitle.getText();
        TestCache.put(PRODUCT_NAME, productName);
    }
}
