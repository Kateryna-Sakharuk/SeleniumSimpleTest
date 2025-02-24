package core.report;


import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class ScreenshotUtil {
    static final Logger logger = LogManager.getLogger("");

    private ScreenshotUtil() {
    }

    @Attachment(value = "{0}", type = "image/png")
    public static byte[] makeScreenshot(String name, WebDriver driver) {
        try {
            logger.info(String.format("Taking screenshot: %s", name));
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            return screenshotBytes;
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: ", e);
            return new byte[0];
        }
    }
}
