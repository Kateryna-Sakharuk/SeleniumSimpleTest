package pageobject.amazon;

import core.driver.IWebDriverProvider;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobject.AbstractPage;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends AbstractPage {
    @FindBy(xpath = "//div[@id='search']")
    List<WebElement> listOfResults;
    public SearchResultPage(IWebDriverProvider driver) {
        super(driver);
        PageFactory.initElements(driver.getWebDriver(), this);
    }
    @Step("Click on the first product in the search results")
    public void clickOnFirstProduct() {
        listOfResults.get(0).click();
    }
    @Step("Get the list of product titles from the search results")
    public List<String> getProductResult() {
        List<String> productResult = new ArrayList<>();
        for (WebElement product : listOfResults) {
            productResult.add(product.getText());
        }
        return productResult;
    }
}
