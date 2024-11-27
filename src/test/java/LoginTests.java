import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageobject.HomePage;
import pageobject.LoginPage;

import static core.Property.*;
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
    public void getUserAuthorization() {
        homePage.openHomePage();
        homePage.singIn();
        loginPage.enterEmail(EMAIL.readProperty());
        loginPage.clickContinueButton();
        loginPage.enterPassword(PASSWORD.readProperty());
        loginPage.clickLogin();

        assertTrue(homePage.isHomePageMainElementDisplayed(), "HomePage should be displayed");
        assertTrue(homePage.isAccountNameContainsText("Hello, Katya"), "AccountName should be displayed");
    }
}




