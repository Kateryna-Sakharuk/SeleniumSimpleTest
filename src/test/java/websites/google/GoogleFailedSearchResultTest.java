package websites.google;
import core.properties.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobject.google.GoogleHomePage;
import pageobject.google.GoogleSearchResultPage;
import websites.BaseTest;

    public class GoogleFailedSearchResultTest extends BaseTest {
        GoogleHomePage googleHomePage;
        GoogleSearchResultPage googleSearchResultPage;

        @BeforeMethod
        @Parameters("browserName")
        public void setUpPages() {
            googleHomePage = new GoogleHomePage(driver);
            googleSearchResultPage = new GoogleSearchResultPage(driver);
        }

        @Test
        public void searchProductContainsParameterTest() {
            googleHomePage.openHomePage();
            googleHomePage.productSearch(PropertyReader.getProperty("search.parameters"));

            SoftAssert softAssert = new SoftAssert();
            for (String product : googleSearchResultPage.getGoogleProductResult()) {
                Assert.assertTrue(product.toLowerCase()
                                .contains(PropertyReader.getProperty("another.search.parameters").toLowerCase()),
                        "Product result does not contain the search parameter: " + PropertyReader.getProperty("another.search.parameters").toLowerCase());
            }
            softAssert.assertAll();
        }
    }

