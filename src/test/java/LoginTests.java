
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageobject.HomePage;
import pageobject.LoginPage;

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
        homePage.openHomePage(Property.BASE_URL);
        Assertions.assertTrue(homePage.isCurrentUrlContains(Property.WEBSITE_NAME));
        Assertions.assertTrue(homePage.isAccountLinkDisplayed());
        loginPage.singIn();
        Assertions.assertTrue(loginPage.isCurrentUrlContains(Property.SIGN_IN_NAME));
        loginPage.enterEmail(TestData.EMAIL);
        loginPage.clickContinueButton();
        loginPage.enterPassword(TestData.PASSWORD);
        loginPage.clickLogin();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(homePage.isHomePageDisplayed()).isEqualTo(true);
        softAssertions.assertThat(homePage.isAccountNameDisplayed()).isEqualTo(true);
        softAssertions.assertAll();
    }
}




