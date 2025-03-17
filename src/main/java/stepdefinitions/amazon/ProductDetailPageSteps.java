package stepdefinitions.amazon;

import io.cucumber.java.en.When;
import pageobject.amazon.ProductDetailPage;
import stepdefinitions.CucumberHooks;

public class ProductDetailPageSteps {
    private final ProductDetailPage productDetailPage = new ProductDetailPage(CucumberHooks.getDriver());

    @When("Authorized user adds the product to the shopping list")
    public void theUserAddsTheProductToTheShoppingList() {
        productDetailPage.addProductToShoppingList();
    }

    @When("Save the first product's name to the test cache")
    public void saveFirstProductNameToTheTestCache() {
        productDetailPage.saveFirstProductName();
    }
}
