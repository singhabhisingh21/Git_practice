package source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class get_page_source {

    /**
     * This main method will launch a browser, navigate to the specified URL,
     * get the page's HTML source code, and save it to a file in the
     * 'src/test/resources' directory.
     *
     * Run this method once to get the HTML file.
     */
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            // Navigate to the URL
            driver.get("https://rahulshettyacademy.com/AutomationPractice/");

            // Get the page source
            String pageSource = driver.getPageSource();
            System.out.println("Successfully fetched page source.");

            // Define the path and ensure the resources directory exists
            File resourcesDir = new File("src/test/resources");
            if (!resourcesDir.exists()) {
                resourcesDir.mkdirs();
            }
            String filePath = resourcesDir.getAbsolutePath() + "/page_source.html";

            // Write the source to a file
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(pageSource);
                System.out.println("Successfully saved page source to: " + filePath);
            } catch (IOException e) {
                System.err.println("Error writing page source to file: " + e.getMessage());
                e.printStackTrace();
            }

        } finally {
            // Close the browser
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
