package core.report;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.xml.XmlSuite;

public class TestReporter implements IReporter {
    static final Logger logger = LogManager.getLogger("");

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
                               String outputDirectory) {
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> results = suite.getResults();
            for (Map.Entry<String, ISuiteResult> entry : results.entrySet()) {
                ITestContext context = entry.getValue().getTestContext();
                logTestSuiteSummary(context);
                logFailedTestCases(context);
            }
        }
    }
    private void logTestSuiteSummary(ITestContext context) {
        logger.info("\n--------------- Suite Details ---------------");
        logger.info("Suite Name: {}", context.getName());
        logger.info("Report Output Directory: {}", context.getOutputDirectory());
        logger.info("Suite Start Time: {}", context.getStartDate());
        logger.info("Suite End Time: {}", context.getEndDate());
        logger.info("Suite: {}", context.getSuite().getName());
        logger.info("--------------------------------------------\n");
    }

    private void logFailedTestCases(ITestContext context) {
        if (!context.getFailedTests().getAllMethods().isEmpty()) {
            Collection<ITestNGMethod> failedMethods = context.getFailedTests().getAllMethods();
            logger.info("-------- FAILED TEST CASES --------");

            for (ITestNGMethod method : failedMethods) {
                logger.error("Test Case Name: {}", method.getMethodName());
                logger.error("Description: {}", method.getDescription());
                logger.error("Priority: {}", method.getPriority());
                logger.error("Failure Date: {}", new Date(method.getDate()));
            }
            logger.info("----------------------------------------\n");
        }
    }
}