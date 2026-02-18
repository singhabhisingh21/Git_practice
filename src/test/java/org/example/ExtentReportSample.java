package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

/**
 * A sample class demonstrating how to generate an Extent Report with Selenium WebDriver.
 */
public class ExtentReportSample {

    public ExtentReports extent;
    public ExtentTest test;
    public WebDriver driver;

    /**
     * This method runs once before all tests in this class.
     * It sets up the ExtentReports object and the HTML reporter.
     */
    @BeforeTest
    public void setupReport() {
        // Define the path for the report
        String reportPath = System.getProperty("user.dir") + "/test-output/extent-report.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

        // Configure the report's appearance
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Sample Execution Report");
        sparkReporter.config().setTheme(Theme.STANDARD);

        // Create the main ExtentReports object and attach the reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Jane Doe");
        extent.setSystemInfo("Environment", "QA");
    }

    /**
     * This method runs before each @Test method.
     * It creates a new test entry in the report and sets up the WebDriver.
     */
    @BeforeMethod
    public void setup(Method method) {
        // Create a new test in the report, using the test method's name
        test = extent.createTest(method.getName());
        // Setup WebDriver
        driver = new ChromeDriver();
        test.log(Status.INFO, "Starting test: " + method.getName());
        test.log(Status.INFO, "Driver initialized.");
    }

    @Test
    public void successfulGoogleTest() {
        test.log(Status.INFO, "Navigating to Google.");
        driver.get("https://www.google.com");
        Assert.assertEquals(driver.getTitle(), "Google");
        test.log(Status.PASS, "Title verification successful.");
    }

    @Test
    public void failingBingTest() {
        test.log(Status.INFO, "Navigating to Bing.");
        driver.get("https://www.bing.com");
        // This assertion will fail intentionally to demonstrate failure logging
        Assert.assertEquals(driver.getTitle(), "Google");
    }

    /**
     * This method runs after each @Test method.
     * It logs the test status and closes the WebDriver.
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Passed");
        } else {
            test.log(Status.SKIP, "Test Skipped");
        }

        // Close the browser to prevent resource leaks
        if (driver != null) {
            driver.quit();
            test.log(Status.INFO, "Driver closed.");
        }
    }

    /**
     * This method runs once after all tests in this class.
     * It writes all the test information to the report file.
     */
    @AfterTest
    public void flushReport() {
        extent.flush();
    }
}
