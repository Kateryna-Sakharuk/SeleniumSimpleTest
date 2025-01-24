package core.report;

import org.testng.*;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FailedClassesAnalyzer implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        Set<String> failedClasses = new HashSet<>();
        for (ISuite suite : suites) {
            for (ISuiteResult result : suite.getResults().values()) {
                ITestContext testContext = result.getTestContext();
                if (testContext.getFailedTests().size() > 0) {
                    for (ITestResult failedTest : testContext.getFailedTests().getAllResults()) {
                        failedClasses.add(failedTest.getTestClass().getName());
                    }
                }
            }
        }

        if (!failedClasses.isEmpty()) {
            generateXmlFile(failedClasses, "target");
        }
    }


    private void generateXmlFile(Set<String> failedClasses, String outputDirectory) {
        XmlSuite suite = new XmlSuite();
        suite.setName("FailedTestsSuite");

        XmlTest test = new XmlTest(suite);
        test.setName("FailedTests");

        List<XmlClass> classes = new ArrayList<>();
        for (String failedClass : failedClasses) {
            classes.add(new XmlClass(failedClass));
        }
        test.setXmlClasses(classes);

        String filePath = outputDirectory + "\\failed-tests.xml";
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(suite.toXml());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
