package core.report;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestResultsListener implements ITestListener{

    static final Logger logger = LogManager.getLogger("");

    @Override
    public void onTestStart(ITestResult result) {
        logger.info(result.getTestClass().getName() + "." + result.getMethod().getMethodName() + " --- Test case STARTED ---");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info(result.getTestClass().getName() + "." + result.getMethod().getMethodName() + " --- Test case PASSED ---");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error(result.getTestClass().getName() + "." + result.getMethod().getMethodName() + " --- Test case FAILED ---");
        ScreenshotUtil.makeScreenshot("Failure screen", (WebDriver) result.getTestContext().getAttribute("WebDriver"));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.error(result.getTestClass().getName() + "." + result.getMethod().getMethodName() + " --- Test case SKIPPED ---");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.warn(result.getTestClass().getName() + "." + result.getMethod().getMethodName() + " --- Test case FAILED but within success percentage ---");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        logger.error(result.getTestClass().getName() + "." + result.getMethod().getMethodName()
                + " --- Test case FAILED DUE TO TIMEOUT ---");
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info(" \n --------------- Test Execution STARTED --------------- \n ");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info(" \n --------------- Test Execution ENDED --------------- \n");
    }
}

