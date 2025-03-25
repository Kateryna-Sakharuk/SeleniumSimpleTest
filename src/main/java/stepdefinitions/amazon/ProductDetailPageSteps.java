package stepdefinitions.amazon;

import hook.CucumberHooks;
import io.cucumber.java.en.And;
import pageobject.amazon.ProductDetailPage;

public class ProductDetailPageSteps {
    private final ProductDetailPage productDetailPage = new ProductDetailPage(CucumberHooks.getDriver());

    @And("Authorized user adds the product to the shopping list")
    public void theUserAddsTheProductToTheShoppingList() {
        productDetailPage.addProductToShoppingList();
    }

    @And("Save the first product's name to the test cache")
    public void saveFirstProductNameToTheTestCache() {
        productDetailPage.saveFirstProductName();
    }
}
