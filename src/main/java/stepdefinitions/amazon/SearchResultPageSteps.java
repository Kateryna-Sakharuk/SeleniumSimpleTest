package stepdefinitions.amazon;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageobject.amazon.SearchResultPage;
import stepdefinitions.CucumberHooks;

import java.util.List;

public class SearchResultPageSteps {
    private final SearchResultPage searchResultPage = new SearchResultPage(CucumberHooks.getDriver());
    private List<String> searchResults;

    @When("User clicks on the first product in the search results")
    public void theUserClicksOnTheFirstProductInTheSearchResults() {
        searchResultPage.clickOnFirstProduct();
    }

    @When("User retrieves the list of product titles from the search results")
    public void theUserRetrievesTheListOfProductTitles() {
        searchResults = searchResultPage.getProductResult();
    }

    @Then("The search results should not be empty")
    public void theSearchResultsShouldNotBeEmpty() {
        Assert.assertFalse(searchResults.isEmpty(), "Search results should not be empty");
    }
}
