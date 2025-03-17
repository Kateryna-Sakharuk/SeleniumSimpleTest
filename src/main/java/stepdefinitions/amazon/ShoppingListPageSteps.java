package stepdefinitions.amazon;
import io.cucumber.java.en.Then;

import org.testng.Assert;
import pageobject.amazon.ShoppingList;
import stepdefinitions.CucumberHooks;


public class ShoppingListPageSteps {
    private final ShoppingList shoppingList = new ShoppingList(CucumberHooks.getDriver());
    private String firstAddedProductName;

    @Then("The first product added to the shopping list should be match the expected product")
    public void theFirstProductAddedToTheShoppingListShouldBe(String expectedProductName) {
        firstAddedProductName = shoppingList.getFirstProductName();
        Assert.assertEquals(expectedProductName, firstAddedProductName,
                "The first product in the shopping list does not match the expected product.");
    }
}

