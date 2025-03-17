package stepdefinitions.amazon;

import io.cucumber.java.en.When;
import pageobject.amazon.AddToListModal;
import stepdefinitions.CucumberHooks;

public class AddToListModalSteps {
    private final AddToListModal addToListModal = new AddToListModal(CucumberHooks.getDriver());

    @When("Authorized user clicks on the 'View Your List' button")
    public void theUserClicksOnTheViewYourListButton() {
        addToListModal.clickYourListButton();
    }
}
