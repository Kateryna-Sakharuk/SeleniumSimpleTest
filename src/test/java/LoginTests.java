import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageobject.HomePage;
import pageobject.LoginPage;

import static core.properties.Property.*;
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
        loginPage.signInWithCredentials(EMAIL.readProperty(), PASSWORD.readProperty());

        homePage.waitUntilPageLoaded();
        assertTrue(homePage.isAmazonHeaderLogoDisplayed(), "Amazon logo should be displayed");
        assertTrue(homePage.isHomePageMainBodyDisplayed(), "Home Page body should be displayed");
        assertTrue(homePage.isAccountNameContainsText(AUTHORISE_USER_NAME.readProperty()), "Account name should be displayed");
    }
}




