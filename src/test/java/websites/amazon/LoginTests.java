package websites.amazon;

import core.properties.PropertyReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobject.amazon.HomePage;
import pageobject.amazon.LoginPage;
import websites.BaseTest;

import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    @Parameters("browserName")
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        BaseTest.logger.info("set up pages message");
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




