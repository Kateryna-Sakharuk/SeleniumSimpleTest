package websites.google;

import core.properties.PropertyReader;
import core.report.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobject.google.GoogleHomePage;
import pageobject.google.GoogleSearchResultPage;
import websites.BaseTest;

import java.util.List;

public class GoogleSearchResultTest extends BaseTest {
    GoogleHomePage googleHomePage;
    GoogleSearchResultPage googleSearchResultPage;

    @BeforeMethod
    @Parameters("browserName")
    public void setUpPages() {
        googleHomePage = new GoogleHomePage(driver);
        googleSearchResultPage = new GoogleSearchResultPage(driver);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void searchProductContainsParameterTest() {
        googleHomePage.openHomePage();
        googleHomePage.productSearch(PropertyReader.getProperty("search.parameters"));

        SoftAssert softAssert = new SoftAssert();
        List<String> googleProductsResult = googleSearchResultPage.getGoogleProductResult();
        softAssert.assertTrue(googleProductsResult.size() >= 0);
        for (String product : googleSearchResultPage.getGoogleProductResult()) {
            Assert.assertTrue(product.toLowerCase()
                            .contains(PropertyReader.getProperty("search.parameters").toLowerCase()),
                    "Product result does not contain the search parameter: " + PropertyReader.getProperty("search.parameters").toLowerCase());
        }
        softAssert.assertAll();
    }
}
