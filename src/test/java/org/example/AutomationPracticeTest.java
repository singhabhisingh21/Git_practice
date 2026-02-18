package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class AutomationPracticeTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Selenium 4.6+ includes Selenium Manager which automatically downloads the required driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testPageInteractions() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        System.out.println("Page title is: " + driver.getTitle());

        // --- Radio Button and Dropdown ---
        driver.findElement(By.cssSelector("input[value='radio1']")).click();
        System.out.println("Clicked on Radio1");

        WebElement dropdownElement = driver.findElement(By.id("dropdown-class-example"));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText("Option2");
        System.out.println("Selected Option2 from dropdown");
        Thread.sleep(2000); // Pause to show Option2

        dropdown.selectByVisibleText("Option3");
        System.out.println("Selected Option3 from dropdown");

        // --- Switch Window Example ---
        String originalWindowHandle = driver.getWindowHandle();
        driver.findElement(By.id("openwindow")).click();
        System.out.println("Clicked 'Open Window' button");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (!handle.equals(originalWindowHandle)) {
                driver.switchTo().window(handle);
                System.out.println("Switched to new window");
                break;
            }
        }

        // On the new page, wait for the 'Courses' link to be clickable and then click it
        WebElement coursesLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Courses']")));
        coursesLink.click();
        System.out.println("Clicked 'Courses' on the new window");
        Thread.sleep(3000); // Pause to see the courses page

        // Close the new window and switch back to the original
        driver.close();
        driver.switchTo().window(originalWindowHandle);
        System.out.println("Closed new window and switched back to original");

        // Final pause on the original page
        Thread.sleep(2000);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}