import core.properties.PropertyReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageobject.HomePage;
import pageobject.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;

    @BeforeEach
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @Test
    public void userAuthorizationTest() {
        homePage.openHomePage();
        homePage.clickSingInButton();
        loginPage.signInWithCredentials(PropertyReader.getProperty("email"), PropertyReader.getProperty("password"));

        homePage.waitUntilPageLoaded();
        assertTrue(homePage.isAmazonHeaderLogoDisplayed(), "Amazon logo should be displayed");
        assertTrue(homePage.isHomePageMainBodyDisplayed(), "Home Page body should be displayed");
        assertTrue(homePage.isAccountNameContainsText(PropertyReader.getProperty("authorise.user.name")), "Account name should be displayed");
    }
}




