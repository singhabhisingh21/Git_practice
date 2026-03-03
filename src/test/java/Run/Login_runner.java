package Run;

import Pages.Login_page;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Login_runner extends Base_test {

    /**
     * Test method to perform login using data from the JSON file.
     *
     * @param data A HashMap containing the username and password for the current test iteration.
     * @throws InterruptedException for Thread.sleep
     */
    @Test(dataProvider = "getLoginData")
    public void performLogin(HashMap<String, String> data) throws InterruptedException {
        // Navigate to the login page
        driver.get("https://practicetestautomation.com/practice-test-login/");

        // Create an instance of the Login Page Object
        Login_page loginPage = new Login_page(driver);

        // Perform the login action using data from the data provider
        loginPage.login(data.get("username"), data.get("password"));

        // Add a small pause to observe the result
        Thread.sleep(2000);

        // Here you would typically add assertions to verify successful login
        // or the presence of an error message.
        // For example: Assert.assertTrue(driver.getCurrentUrl().contains("logged-in-successfully"));
    }

    /**
     * DataProvider method to fetch login credentials from the JSON file.
     *
     * @return An object array where each object is a HashMap of credentials.
     * @throws IOException if the JSON file cannot be read.
     */
    @DataProvider
    public Object[][] getLoginData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(
                System.getProperty("user.dir") + "/src/test/resources/credentials.json"
        );

        // Convert the List of HashMaps to a 2D Object array required by TestNG's DataProvider
        Object[][] dataArray = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            dataArray[i][0] = data.get(i);
        }
        return dataArray;
    }
}
