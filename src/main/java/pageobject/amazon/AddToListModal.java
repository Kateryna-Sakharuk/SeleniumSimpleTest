package pageobject.amazon;

import core.driver.IWebDriverProvider;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobject.AbstractPage;

public class AddToListModal extends AbstractPage {
    @FindBy(xpath = "//span[@id = 'huc-view-your-list-button']//a[contains(text(), 'View Your List')]")
    WebElement viewYourListButton;

    public AddToListModal(IWebDriverProvider driver) {
        super(driver);
        PageFactory.initElements(driver.getWebDriver(), this);
    }
    @Step("Click on Your List button")
    public void clickYourListButton() {
        waitUntilElementVisible(viewYourListButton);
        viewYourListButton.click();
    }
}
