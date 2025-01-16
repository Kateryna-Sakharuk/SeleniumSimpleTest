package pageobject.amazon;

import core.driver.IWebDriverProvider;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobject.AbstractPage;

public class ShoppingList extends AbstractPage {
    @FindBy(xpath = "//h2//a[@class='a-link-normal']")
    WebElement shoppingListAddedItem;

    public ShoppingList(IWebDriverProvider driver) {
        super(driver);
        PageFactory.initElements(driver.getWebDriver(), this);
    }
    @Step("Get the name of the first product added to the shopping list")
    public String getFirstProductName() {
        return shoppingListAddedItem.getText();
    }
}
