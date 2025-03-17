package stepdefinitions.amazon;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageobject.amazon.HomePage;
import stepdefinitions.CucumberHooks;

public class HomePageSteps {
    private final HomePage homePage = new HomePage(CucumberHooks.getDriver());

    @Given("Anonymous user opens the Amazon home page")
    public void theUserOpensAmazonHomepage() {
        homePage.openHomePage();
    }

    @When("Anonymous user clicks on the Sign In button")
    public void theUserClicksOnSignInButton() {
        homePage.clickSingInButton();
    }

    @Then("The Amazon header logo should be displayed")
    public void amazonHeaderLogoShouldBeDisplayed() {
        Assert.assertTrue(homePage.isAmazonHeaderLogoDisplayed(),
                "Amazon header logo is not displayed!");
    }

    @And("The homepage main body should be visible")
    public void homepageMainBodyShouldBeVisible() {
        Assert.assertTrue(homePage.isHomePageMainBodyDisplayed(),
                "Homepage main body is not displayed!");
    }

    @And("The account name should contain {string}")
    public void accountNameShouldContain(String expectedAccountName) {
        Assert.assertTrue(homePage.isAccountNameContainsText(expectedAccountName),
                "Account name does not contain expected text: " + expectedAccountName);
    }

    @When("User searches for a product with the parameter {string}")
    public void userSearchesForProduct(String productName) {
        homePage.productSearch(productName);

    }
}