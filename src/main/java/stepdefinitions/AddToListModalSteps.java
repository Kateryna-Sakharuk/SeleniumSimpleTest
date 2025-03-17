package stepdefinitions;

import io.cucumber.java.en.When;
import pageobject.amazon.AddToListModal;

public class AddToListModalSteps {
    private final AddToListModal addToListModal = new AddToListModal(CucumberHooks.getDriver());

    @When("Authorized user clicks on the 'View Your List' button")
    public void theUserClicksOnTheViewYourListButton() {
        addToListModal.clickYourListButton();
    }
}
