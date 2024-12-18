
import core.properties.PropertyReader;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageobject.*;

public class SearchResultTest extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    SearchResultPage searchResultPage;

    @BeforeEach
    public void setUpPages() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        searchResultPage = new SearchResultPage(driver);
    }

    @Test
    public void searchProductContainsParameterTest(){

        homePage.openHomePage();
        homePage.clickSingInButton();
        loginPage.signInWithCredentials(PropertyReader.getProperty("email"), PropertyReader.getProperty("password"));
        homePage.productSearch(PropertyReader.getProperty("search.parameters"));

        SoftAssertions assertions = new SoftAssertions();
        for (String product : searchResultPage.getProductResult()) {
            assertions.assertThat(product.toLowerCase())
                    .as("Product result does not contain the search parameter: " + PropertyReader.getProperty("search.parameters").toLowerCase())
                    .contains(PropertyReader.getProperty("search.parameters").toLowerCase());
        }
        assertions.assertAll();
    }
}
