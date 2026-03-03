package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login_page {

    private final WebDriver driver;

    // --- Element Locators ---
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By submitButton = By.id("submit");

    /**
     * Constructor to initialize the page with a WebDriver instance.
     * @param driver The WebDriver instance from your test.
     */
    public Login_page(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Enters the username and password, then clicks the submit button.
     * @param username The username to enter.
     * @param password The password to enter.
     */
    public void login(String username, String password) {
        driver.findElement(usernameInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(submitButton).click();
        System.out.println("Attempted login with Username: " + username);
    }
}
