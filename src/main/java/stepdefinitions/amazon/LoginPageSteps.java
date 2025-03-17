package stepdefinitions.amazon;

import io.cucumber.java.en.When;
import pageobject.amazon.LoginPage;
import stepdefinitions.CucumberHooks;


public class LoginPageSteps {
    private final LoginPage loginPage = new LoginPage(CucumberHooks.getDriver());

    @When("User logs in with email {string} and password {string}")
    public void theUserLogsInWithEmailAndPassword(String email, String password) {
        loginPage.signInWithCredentials(email, password);
    }
}
