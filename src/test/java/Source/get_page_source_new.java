package source;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

public class get_page_source_new {

    /**
     * This main method will:
     * 1. Open the original practice page.
     * 2. Click the "Open Window" button.
     * 3. Switch to the new window.
     * 4. Get the HTML source of the NEW window.
     * 5. Save it to 'src/test/resources/new_window_source.html'.
     */
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Navigate to the original page to start the process
            driver.get("https://rahulshettyacademy.com/AutomationPractice/");
            String originalWindow = driver.getWindowHandle();

            // Click the button that opens the new window
            driver.findElement(By.id("openwindow")).click();

            // Wait for the new window to appear and switch to it
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            for (String windowHandle : driver.getWindowHandles()) {
                if (!originalWindow.contentEquals(windowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
            System.out.println("Switched to new window with title: " + driver.getTitle());

            // Get the source of the NEW page
            String newPageSource = driver.getPageSource();
            System.out.println("Successfully fetched new page source.");

            // Define the path and save the new source to a DIFFERENT file
            File resourcesDir = new File("src/test/resources");
            if (!resourcesDir.exists()) {
                resourcesDir.mkdirs();
            }
            String filePath = resourcesDir.getAbsolutePath() + "/new_window_source.html";

            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(newPageSource);
                System.out.println("Successfully saved new page source to: " + filePath);
            } catch (IOException e) {
                System.err.println("Error writing new page source to file: " + e.getMessage());
            }

        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
