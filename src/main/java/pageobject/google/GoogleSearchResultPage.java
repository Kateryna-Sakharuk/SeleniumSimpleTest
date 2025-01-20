package pageobject.google;

import core.driver.IWebDriverProvider;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobject.AbstractPage;

import java.util.ArrayList;
import java.util.List;

public class GoogleSearchResultPage extends AbstractPage {

    public GoogleSearchResultPage(IWebDriverProvider driver) {
        super(driver);
        PageFactory.initElements(driver.getWebDriver(), this);
    }
    @FindBy(xpath = "//*[@id=\"rso\"]/div")
    List<WebElement> listOfResults;
    @Step("Get the list of product titles from the search results")
    public List<String> getGoogleProductResult() {
        List<String> productResult = new ArrayList<>();
        for (WebElement product : listOfResults) {
            productResult.add(product.getText());
        }
        return productResult;
    }
}
