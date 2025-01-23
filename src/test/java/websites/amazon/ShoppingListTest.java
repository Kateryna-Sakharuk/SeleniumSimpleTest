package websites.amazon;

import core.cache.TestCache;
import core.properties.PropertyReader;
import core.report.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobject.amazon.AddToListModal;
import pageobject.amazon.HomePage;
import pageobject.amazon.LoginPage;
import pageobject.amazon.ProductDetailPage;
import pageobject.amazon.SearchResultPage;
import pageobject.amazon.ShoppingList;
import websites.BaseTest;

import static core.cache.TestCacheKey.PRODUCT_NAME;

public class ShoppingListTest extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    SearchResultPage searchResultPage;
    ProductDetailPage productDetailPage;
    ShoppingList shoppingList;
    AddToListModal addToListModal;

    @BeforeMethod
    @Parameters("browserName")
    public void setUpPages() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        searchResultPage = new SearchResultPage(driver);
        productDetailPage = new ProductDetailPage(driver);
        shoppingList = new ShoppingList(driver);
        addToListModal = new AddToListModal(driver);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void searchProductTest() {
        homePage.openHomePage();
        homePage.clickSingInButton();
        loginPage.signInWithCredentials(PropertyReader.getProperty("email"), PropertyReader.getProperty("password"));
        homePage.productSearch(PropertyReader.getProperty("search.parameters"));
        searchResultPage.clickOnFirstProduct();
        productDetailPage.saveFirstProductName();
        productDetailPage.addProductToShoppingList();
        addToListModal.clickYourListButton();

        Assert.assertEquals(shoppingList.getFirstProductName(), TestCache.getStringValue(PRODUCT_NAME), "The names do not match!");
    }
}
