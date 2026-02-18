package Run;

import Pages.Practice_page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class practicepage_run {

    private WebDriver driver;
    private Practice_page practicePage;

    /**
     * This method sets up the WebDriver and navigates to the page before each test.
     */
    @BeforeMethod
    public void setup() {
        // Initialize the WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to the practice page
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // Initialize the Page Object, passing the driver instance to it
        practicePage = new Practice_page(driver);
    }

    /**
     * This test method executes the actions defined in the Practice_page class.
     * @throws InterruptedException for Thread.sleep
     */
    @Test
    public void executePracticePageMethods() throws InterruptedException {
        // Action 1: Click the radio button
        practicePage.clickRadio1();
        Thread.sleep(500);

        // Action 2: Select a country from the suggestion list
        practicePage.selectCountry("ind", "India");
        Thread.sleep(500);

        // Action 3: Select an option from the dropdown
        practicePage.selectDropdownOption("Option2");
        Thread.sleep(500);

        // Action 4: Select checkboxes
        practicePage.selectCheckbox("option1");
        Thread.sleep(500);
        practicePage.selectCheckbox("option2");
        Thread.sleep(500);

        // Action 5: Switch to the new window
        String originalWindow = practicePage.switchToNewWindow();
        Thread.sleep(500);

        // Action 6: Interact with the new window
        practicePage.clickCoursesOnNewWindow();
        Thread.sleep(1000);

        // Close the new window and switch back
        driver.close();
        driver.switchTo().window(originalWindow);
        System.out.println("Closed new window and switched back to original.");
        Thread.sleep(500);

        // Action 7: Click Open Tab and switch to it
        String originalTab = practicePage.clickOpenTabAndSwitch();
        Thread.sleep(1000);

        // Close the new tab and switch back
        driver.close();
        driver.switchTo().window(originalTab);
        System.out.println("Closed new tab and switched back to original.");
        Thread.sleep(500);

        // Action 8: Alert and Confirm interactions
        System.out.println("\n--- Action 8: Alert and Confirm Interactions ---");
        practicePage.enterName("Abhishek");
        Thread.sleep(500);
        practicePage.clickAlertAndAccept();
        Thread.sleep(500);
        practicePage.enterName("Abhishek");
        Thread.sleep(500);
        practicePage.clickConfirmAndAccept();
        Thread.sleep(500);
        practicePage.enterName("Abhishek");
        Thread.sleep(500);
        practicePage.clickConfirmAndDismiss();
        Thread.sleep(500);

        // Action 9: Element Displayed Example
        System.out.println("\n--- Action 9: Element Displayed Example ---");
        practicePage.enterTextInDisplayedBox("hide");
        Thread.sleep(500);
        practicePage.clickHideButton();
        Thread.sleep(500);
        practicePage.clickShowButton();
        Thread.sleep(500);
        practicePage.enterTextInDisplayedBox("show");
        Thread.sleep(500);

        // Action 10: Fixed Header Table
        System.out.println("\n--- Action 10: Fixed Header Table ---");
        practicePage.scrollFixedHeaderTable();
        Thread.sleep(500);
        int totalAmount = practicePage.getSumFromAmountColumn();
        // Optional: You can add an assertion to verify the sum
        Assert.assertEquals(totalAmount, 296);
        System.out.println("Assertion passed: Total amount is correct.");
        Thread.sleep(500);

        // Action 11: Mouse Hover Actions
        System.out.println("\n--- Action 11: Mouse Hover Actions ---");
        practicePage.performMouseHoverAndClickActions();
        Thread.sleep(1000);

        // Action 12: Iframe Actions
        System.out.println("\n--- Action 12: Iframe Actions ---");
        practicePage.interactWithIframeAndHomePage();
        Thread.sleep(2000); // Final pause
    }

    /**
     * This method closes the WebDriver after each test to clean up resources.
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
