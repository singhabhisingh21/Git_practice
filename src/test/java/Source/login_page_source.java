package Source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;

/**
 * This class contains a test method to navigate to a URL,
 * retrieve its full page source, and save it to an HTML file.
 */
public class login_page_source {

    /**
     * Navigates to the practice login page, gets its source code,
     * and saves it into the 'src/test/resources' directory.
     *
     * @throws IOException if there is an error writing the file.
     */
    @Test
    public void savePageSourceToFile() throws IOException {
        // 1. Initialize a new WebDriver instance
        WebDriver driver = new ChromeDriver();
        System.out.println("Chrome browser initialized.");

        try {
            // 2. Navigate to the target URL
            String url = "https://practicetestautomation.com/practice-test-login/";
            driver.get(url);
            System.out.println("Navigated to: " + url);

            // 3. Get the entire page source code
            String pageSource = driver.getPageSource();
            System.out.println("Successfully retrieved the page source.");

            // 4. Define the path and save the source to a file
            String filePath = "src/test/resources/login_page.html";
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(pageSource);
                System.out.println("Page source has been successfully saved to: " + filePath);
            }

        } finally {
            // 5. Ensure the browser is closed
            if (driver != null) {
                driver.quit();
                System.out.println("Browser has been closed.");
            }
        }
    }
}
