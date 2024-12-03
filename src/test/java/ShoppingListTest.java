import core.cache.TestCache;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageobject.*;

import static core.properties.Property.*;
import static core.cache.TestCacheKey.PRODUCT_NAME;

public class ShoppingListTest extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    SearchResultPage searchResultPage;
    ProductDetailPage productDetailPage;
    ShoppingList shoppingList;


    @BeforeEach
    public void setUpPages() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        searchResultPage = new SearchResultPage(driver);
        productDetailPage = new ProductDetailPage(driver);
        shoppingList = new ShoppingList(driver);
    }

    @Test
    public void SearchProductTest() {

        homePage.openHomePage();
        homePage.clickSingInButton();
        loginPage.signInWithCredentials(EMAIL.readProperty(), PASSWORD.readProperty());

        homePage.productSearch(SEARCH_PARAMETERS.readProperty());
        searchResultPage.ClickOnFirstProduct();
        productDetailPage.saveFirstProductName();
        productDetailPage.addProductToShoppingList();
        productDetailPage.clickYourListButton();

        Assertions.assertEquals(TestCache.getStringValue(PRODUCT_NAME), shoppingList.getFirstProductName(), "The names do not match!");

    }
}
