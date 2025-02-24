package pageobject;

import core.driver.IWebDriverProvider;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends AbstractPage {
    @FindBy(xpath = "//div[@data-component-type='s-search-result']//a//h2//span")
    List<WebElement> listOfProducts;
    public SearchResultPage(IWebDriverProvider driver) {
        super(driver);
        PageFactory.initElements(driver.getWebDriver(), this);
    }
    @Step("Click on the first product in the search results")
    public void clickOnFirstProduct() {
        listOfProducts.get(0).click();
    }
    @Step("Get the list of product titles from the search results")
    public List<String> getProductResult() {
        List<String> productResult = new ArrayList<>();
        for (WebElement product : listOfProducts) {
            productResult.add(product.getText());
        }
        return productResult;
    }
}
