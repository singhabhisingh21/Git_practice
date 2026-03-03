package Run;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class browser_initialisation {

    protected WebDriver driver;

    /**
     * Initializes the WebDriver based on properties from Maven or a config file.
     * This method follows a specific order to determine which browser to launch:
     * 1. It checks for a Maven system property: {@code -Dbrowser=...}
     * 2. If no Maven property is found, it reads the 'browser' property from the
     *    {@code config.properties} file.
     * 3. Defaults to Chrome if no property is found in either location.
     *
     * @return A fully initialized WebDriver instance (ChromeDriver or FirefoxDriver).
     * @throws IOException if the properties file cannot be read.
     */
    public WebDriver initializeBrowser() throws IOException {
        Properties prop = new Properties();
        // Correct path for loading resources from the classpath
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            prop.load(fis);
        }

        // 1. Read from Maven command line first
        String browserName = System.getProperty("browser");

        // 2. If not provided by Maven, read from properties file
        if (browserName == null || browserName.isEmpty()) {
            browserName = prop.getProperty("browser");
        }

        // Initialize the driver based on the browser name
        if (browserName.equalsIgnoreCase("firefox")) {
            // Initialize Firefox Driver
            driver = new FirefoxDriver();
            System.out.println("Initialized Firefox browser.");
        } else if (browserName.equalsIgnoreCase("chrome")) {
            // Initialize Chrome Driver
            driver = new ChromeDriver();
            System.out.println("Initialized Chrome browser.");
        } else {
            // Default to Chrome if the property is invalid or not specified
            System.out.println("No valid browser specified. Defaulting to Chrome.");
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        return driver;
    }
}
