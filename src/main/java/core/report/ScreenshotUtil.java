package core.report;


import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class ScreenshotUtil {

    private ScreenshotUtil() {
    }

    @Attachment(value = "{0}", type = "image/png")
    public static byte[] makeScreenshot(String name, WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }



}
