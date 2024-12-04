package pageobject;

import core.driver.IWebDriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToListModal extends AbstractPage {
    @FindBy(xpath = "//span[@id = 'huc-view-your-list-button']//a[contains(text(), 'View Your List')]")
    WebElement viewYourListButton;

    public AddToListModal(IWebDriverProvider driver) {
        super(driver);
        PageFactory.initElements(driver.getWebDriver(), this);
    }
    public void clickYourListButton() {
        waitUntilElementVisible(viewYourListButton);
        viewYourListButton.click();
    }
}
