package Run;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Base_test {

    protected WebDriver driver;

    /**
     * Initializes the WebDriver before each test method.
     */
    @BeforeMethod
    public void initializeDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    /**
     * Tears down the WebDriver after each test method.
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Reads a JSON file and converts it into a List of HashMaps.
     *
     * @param jsonFilePath The path to the JSON file.
     * @return A List of HashMaps, where each map represents a set of test data.
     * @throws IOException if the file cannot be read.
     */
    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        // Create an ObjectMapper instance from the Jackson library
        ObjectMapper mapper = new ObjectMapper();

        // Read the JSON file and convert it to a List of HashMaps
        List<HashMap<String, String>> data = mapper.readValue(
                new File(jsonFilePath),
                new TypeReference<List<HashMap<String, String>>>() {}
        );
        return data;
    }
}
