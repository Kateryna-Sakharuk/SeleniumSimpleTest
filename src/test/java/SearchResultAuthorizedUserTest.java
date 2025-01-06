import core.properties.PropertyReader;
import io.qameta.allure.Step;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pageobject.*;
import org.testng.Assert;

public class SearchResultAuthorizedUserTest extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    SearchResultPage searchResultPage;

    @BeforeMethod
    @Parameters("browserName")
    @Step("Initializing pages for browser: {browserName}")
    public void setUpPages() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        searchResultPage = new SearchResultPage(driver);
    }

    @Test
    @Step("Search for product using parameter")
    public void searchProductContainsParameterTest() {
        homePage.openHomePage();
        homePage.clickSingInButton();
        loginPage.signInWithCredentials(PropertyReader.getProperty("email"), PropertyReader.getProperty("password"));
        homePage.productSearch(PropertyReader.getProperty("search.parameters"));

        SoftAssert softAssert = new SoftAssert();
        for (String product : searchResultPage.getProductResult()) {
            Assert.assertTrue(product.toLowerCase()
                    .contains(PropertyReader.getProperty("search.parameters").toLowerCase()),
                    "Product result does not contain the search parameter: " + PropertyReader.getProperty("search.parameters").toLowerCase());
        }
        softAssert.assertAll();
    }
}
