package pageobject;

import core.driver.IWebDriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends AbstractPage {
    @FindBy(xpath = "//div[@data-component-type='s-search-result']//span[contains(@class, 'a-text-normal')]")
    List<WebElement> listOfProducts;
    public SearchResultPage(IWebDriverProvider driver) {
        super(driver);
        PageFactory.initElements(driver.getWebDriver(), this);
    }

    public void ClickOnFirstProduct() {
        listOfProducts.get(0).click();
    }

    public List<String> getProductResult() {
        List<String> productResult = new ArrayList<>();
        for (WebElement product : listOfProducts) {
            productResult.add(product.getText());
        }
        return productResult;
    }
}
