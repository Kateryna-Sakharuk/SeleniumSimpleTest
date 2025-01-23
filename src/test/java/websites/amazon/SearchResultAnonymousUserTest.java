package websites.amazon;

import core.properties.PropertyReader;
import core.report.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobject.amazon.HomePage;
import pageobject.amazon.LoginPage;
import pageobject.amazon.SearchResultPage;
import websites.BaseTest;

public class SearchResultAnonymousUserTest extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    SearchResultPage searchResultPage;

    @BeforeMethod
    @Parameters("browserName")
    public void setUpPages() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        searchResultPage = new SearchResultPage(driver);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void searchProductContainsParameterTest() {
        homePage.openHomePage();
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
