package pageobject;

import core.driver.IWebDriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingList extends AbstractPage {
    @FindBy(xpath = "//h2//a[@class='a-link-normal']")
    WebElement shoppingListAddedItem;

    public ShoppingList(IWebDriverProvider driver) {
        super(driver);
        PageFactory.initElements(driver.getWebDriver(), this);
    }

    public String getFirstProductName() {
        return shoppingListAddedItem.getText();
    }
}
