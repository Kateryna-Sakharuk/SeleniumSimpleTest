package stepdefinitions.amazon;

import hook.CucumberHooks;
import io.cucumber.java.en.And;
import pageobject.amazon.LoginPage;


public class LoginPageSteps {
    private final LoginPage loginPage = new LoginPage(CucumberHooks.getDriver());

    @And("User logs in with email {string} and password {string}")
    public void theUserLogsInWithEmailAndPassword(String email, String password) {
        loginPage.signInWithCredentials(email, password);
    }
}
