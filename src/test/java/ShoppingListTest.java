import core.cache.TestCache;
import core.properties.PropertyReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageobject.*;

import static core.cache.TestCacheKey.PRODUCT_NAME;

public class ShoppingListTest extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    SearchResultPage searchResultPage;
    ProductDetailPage productDetailPage;
    ShoppingList shoppingList;
    AddToListModal addToListModal;


    @BeforeEach
    public void setUpPages() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        searchResultPage = new SearchResultPage(driver);
        productDetailPage = new ProductDetailPage(driver);
        shoppingList = new ShoppingList(driver);
        addToListModal = new AddToListModal(driver);
    }

    @Test
    public void SearchProductTest() {
        homePage.openHomePage();
        homePage.clickSingInButton();
        loginPage.signInWithCredentials(PropertyReader.getProperty("email"), PropertyReader.getProperty("password"));
        homePage.productSearch(PropertyReader.getProperty("search.parameters"));
        searchResultPage.ClickOnFirstProduct();
        productDetailPage.saveFirstProductName();
        productDetailPage.addProductToShoppingList();
        addToListModal.clickYourListButton();

        Assertions.assertEquals(TestCache.getStringValue(PRODUCT_NAME), shoppingList.getFirstProductName(), "The names do not match!");

    }
}
