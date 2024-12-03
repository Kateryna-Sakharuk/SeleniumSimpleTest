package pageobject;

import core.driver.IWebDriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultPage extends AbstractPage {
    @FindBy(xpath = "//a[@class ='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']")
    List<WebElement> openFirstProduct;

    public SearchResultPage(IWebDriverProvider driver) {
        super(driver);
        PageFactory.initElements(driver.getWebDriver(), this);
    }

    public void ClickOnFirstProduct() {
        openFirstProduct.get(0).click();
    }
}
