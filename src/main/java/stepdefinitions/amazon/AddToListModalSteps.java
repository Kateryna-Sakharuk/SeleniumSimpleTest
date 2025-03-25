package stepdefinitions.amazon;

import hook.CucumberHooks;
import io.cucumber.java.en.And;
import pageobject.amazon.AddToListModal;

public class AddToListModalSteps {
    private final AddToListModal addToListModal = new AddToListModal(CucumberHooks.getDriver());

    @And("Authorized user clicks on the 'View Your List' button")
    public void theUserClicksOnTheViewYourListButton() {
        addToListModal.clickYourListButton();
    }
}
