package Run;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class browser_initialisation {

    protected WebDriver driver;

    /**
     * Initializes the WebDriver based on properties from Maven or a config file.
     * <p>
     * This method follows a specific order to determine which browser to launch:
     * 1. It checks for a Maven system property: {@code -Dbrowser=...}
     * 2. If no Maven property is found, it reads the 'browser' property from the
     *    {@code config.properties} file.
     * 3. Defaults to Chrome if no property is found in either location.
     * <p>
     * Supports 'chrome', 'firefox', and 'chromeheadless'.
     *
     * @return A fully initialized WebDriver instance.
     * @throws IOException if the properties file cannot be read.
     */
    public WebDriver initializeBrowser() throws IOException {
        Properties prop = new Properties();
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
            driver = new FirefoxDriver();
            System.out.println("Initialized Firefox browser.");
            driver.manage().window().maximize();
        } else if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            System.out.println("Initialized Chrome browser.");
            driver.manage().window().maximize();
        } else if (browserName.equalsIgnoreCase("chromeheadless")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440, 900));
            System.out.println("Initialized Chrome browser in headless mode with window size 1920x1080.");
        } else {
            System.out.println("No valid browser specified. Defaulting to Chrome.");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

        return driver;
    }
}
