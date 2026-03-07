package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

/**
 * This class represents the Rahul Shetty Academy Practice Page.
 * It follows the Page Object Model (POM) design pattern, where UI elements
 * and the methods to interact with them are stored.
 */
public class Practice_page {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    // --- Element Locators ---
    private final By radio1Button = By.cssSelector("input[value='radio1']");
    private final By suggestionInput = By.id("autocomplete");
    private final By suggestionList = By.xpath("//ul[@id='ui-id-1']/li/div");
    private final By dropdown = By.id("dropdown-class-example");
    private final By openWindowButton = By.id("openwindow");
    private final By openTabButton = By.id("opentab");
    private final By coursesLinkOnNewPage = By.xpath("//a[text()='Courses']");
    private final By nameInputField = By.id("name");
    private final By alertButton = By.id("alertbtn");
    private final By confirmButton = By.id("confirmbtn");
    private final By displayedTextBox = By.id("displayed-text");
    private final By hideButton = By.id("hide-textbox");
    private final By showButton = By.id("show-textbox");
    private final By mouseHoverButton = By.id("mousehover");
    private final By topLink = By.xpath("//div[@class='mouse-hover-content']/a[text()='Top']");
    private final By reloadLink = By.xpath("//div[@class='mouse-hover-content']/a[text()='Reload']");


    // Locators for Fixed Header Table
    private final By fixedHeaderTableDiv = By.cssSelector(".tableFixHead");
    private final By amountColumnCells = By.xpath("//div[@class='tableFixHead']//td[4]");

    // Locators for Iframe interaction
    private final By iframe = By.id("courses-iframe");
    private final By coursesLinkInIframe = By.linkText("Courses");
    private final By viewAllCoursesButton = By.xpath("//a[contains(text(), 'VIEW ALL COURSES')]");
    private final By homeButton = By.xpath("//header//button[text()='Home']");


    /**
     * Constructor to initialize the page with a WebDriver instance.
     * @param driver The WebDriver instance from your test.
     */
    public Practice_page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    // --- Page Actions/Methods ---

    public void clickRadio1() {
        driver.findElement(radio1Button).click();
        System.out.println("Successfully clicked on 'Radio1'.");
    }

    public void selectCountry(String partialText, String countryToSelect) {
        driver.findElement(suggestionInput).sendKeys(partialText);
        System.out.println("Typed '" + partialText + "' into the suggestion box.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionList));
        List<WebElement> suggestions = driver.findElements(suggestionList);
        for (WebElement suggestion : suggestions) {
            if (suggestion.getText().equalsIgnoreCase(countryToSelect)) {
                suggestion.click();
                System.out.println("Successfully selected '" + countryToSelect + "'.");
                return;
            }
        }
        System.out.println("Could not find '" + countryToSelect + "' in the suggestions.");
    }

    public void selectDropdownOption(String optionText) {
        WebElement dropdownElement = driver.findElement(dropdown);
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(optionText);
        System.out.println("Successfully selected '" + optionText + "' from the dropdown.");
    }

    public void selectCheckbox(String checkboxValue) {
        final String checkboxLocator = "//input[@type='checkbox' and @value='%s']";
        By specificCheckbox = By.xpath(String.format(checkboxLocator, checkboxValue));
        driver.findElement(specificCheckbox).click();
        System.out.println("Successfully clicked checkbox with value '" + checkboxValue + "'.");
    }

    public String switchToNewWindow() {
        String originalWindowHandle = driver.getWindowHandle();
        driver.findElement(openWindowButton).click();
        System.out.println("Clicked the 'Open Window' button.");
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (!handle.equals(originalWindowHandle)) {
                driver.switchTo().window(handle);
                System.out.println("Switched focus to the new window.");
                break;
            }
        }
        return originalWindowHandle;
    }

    public String clickOpenTabAndSwitch() {
        String originalTab = driver.getWindowHandle();
        driver.findElement(openTabButton).click();
        System.out.println("Clicked the 'Open Tab' button.");
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalTab.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                System.out.println("Switched focus to the new tab.");
                break;
            }
        }
        return originalTab;
    }

    public void clickCoursesOnNewWindow() {
        WebElement coursesLink = wait.until(ExpectedConditions.presenceOfElementLocated(coursesLinkOnNewPage));
        js.executeScript("arguments[0].click();", coursesLink);
        System.out.println("Successfully clicked on 'Courses' on the new page using JavaScript.");
    }

    public void enterName(String name) {
        driver.findElement(nameInputField).sendKeys(name);
        System.out.println("Entered name: '" + name + "'.");
    }

    public void clickAlertAndAccept() {
        driver.findElement(alertButton).click();
        System.out.println("Clicked 'Alert' button.");
        wait.until(ExpectedConditions.alertIsPresent()).accept();
        System.out.println("Accepted the alert.");
    }

    public void clickConfirmAndAccept() {
        driver.findElement(confirmButton).click();
        System.out.println("Clicked 'Confirm' button.");
        wait.until(ExpectedConditions.alertIsPresent()).accept();
        System.out.println("Accepted the confirmation.");
    }

    public void clickConfirmAndDismiss() {
        driver.findElement(confirmButton).click();
        System.out.println("Clicked 'Confirm' button.");
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        System.out.println("Dismissed the confirmation.");
    }

    public void enterTextInDisplayedBox(String text) {
        WebElement textBox = driver.findElement(displayedTextBox);
        textBox.clear();
        textBox.sendKeys(text);
        System.out.println("Entered '" + text + "' into the displayed text box.");
    }

    public void clickHideButton() {
        driver.findElement(hideButton).click();
        System.out.println("Clicked 'Hide' button.");
    }

    public void clickShowButton() {
        driver.findElement(showButton).click();
        System.out.println("Clicked 'Show' button.");
    }

    /**
     * Scrolls the fixed header table down and then back up.
     */
    public void scrollFixedHeaderTable() {
        WebElement table = driver.findElement(fixedHeaderTableDiv);
        // Scroll down
        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", table);
        System.out.println("Scrolled fixed header table down.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("InterruptedException during thread sleep: " + e.getMessage());
            Thread.currentThread().interrupt();
        } // For visual confirmation
        // Scroll up
        js.executeScript("arguments[0].scrollTop = 0", table);
        System.out.println("Scrolled fixed header table up.");
    }

    /**
     * Calculates the sum of all values in the 'Amount' column of the fixed header table.
     * @return The integer sum of all amounts.
     */
    public int getSumFromAmountColumn() {
        List<WebElement> amountCells = driver.findElements(amountColumnCells);
        int sum = 0;
        for (WebElement cell : amountCells) {
            sum += Integer.parseInt(cell.getText());
        }
        System.out.println("Calculated sum of amounts: " + sum);
        return sum;
    }

    /**
     * Performs a sequence of actions involving mouse hovering, clicking, and scrolling.
     * 1. Hovers over the 'Mouse Hover' button.
     * 2. Clicks the 'Top' link from the context menu.
     * 3. Scrolls the 'Mouse Hover' button back into view.
     * 4. Hovers over the 'Mouse Hover' button again.
     * 5. Clicks the 'Reload' link from the context menu.
     * @throws InterruptedException for Thread.sleep
     */
    public void performMouseHoverAndClickActions() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement mouseHoverElement = driver.findElement(mouseHoverButton);

        // 1. Hover on 'Mouse Hover'
        actions.moveToElement(mouseHoverElement).perform();
        System.out.println("Hovered over 'Mouse Hover' button.");
        Thread.sleep(1000);

        // 2. Click on 'Top'
        WebElement topLinkElement = wait.until(ExpectedConditions.visibilityOfElementLocated(topLink));
        topLinkElement.click();
        System.out.println("Clicked on 'Top' link.");
        Thread.sleep(1000);

        // 3. Scroll down till 'Mouse Hover' displays again
        js.executeScript("arguments[0].scrollIntoView(true);", mouseHoverElement);
        System.out.println("Scrolled down to 'Mouse Hover' button again.");
        Thread.sleep(1000);

        // 4. Hover again and click on 'Reload'
        actions.moveToElement(mouseHoverElement).perform();
        System.out.println("Hovered over 'Mouse Hover' button again.");
        Thread.sleep(1000);
        WebElement reloadLinkElement = wait.until(ExpectedConditions.visibilityOfElementLocated(reloadLink));
        reloadLinkElement.click();
        System.out.println("Clicked on 'Reload' link.");
    }

    /**
     * Interacts with elements inside the iframe, then returns to the main page.
     * 1. Switches to the iframe.
     * 2. Scrolls to and clicks the 'Courses' link inside the iframe.
     * 3. Navigates back.
     * 4. Re-switches to the iframe.
     * 5. Waits, scrolls to, and clicks the 'View All Courses' button.
     * 6. Switches back to the main page.
     * 7. Clicks the 'Home' button on the main page.
     * @throws InterruptedException for Thread.sleep
     */
    public void interactWithIframeAndHomePage() throws InterruptedException {
        // Switch to the iframe
        driver.switchTo().frame(driver.findElement(iframe));
        System.out.println("Switched to iframe.");
        Thread.sleep(1000);

        // Find the 'Courses' link
        WebElement coursesLink = driver.findElement(coursesLinkInIframe);

        // Scroll to the 'Courses' link
        js.executeScript("arguments[0].scrollIntoView(true);", coursesLink);
        System.out.println("Scrolled to 'Courses' link inside the iframe.");
        Thread.sleep(1000);

        // Click on 'Courses' inside the iframe
        coursesLink.click();
        System.out.println("Clicked on 'Courses' link inside the iframe.");
        Thread.sleep(2000); // Allow time for navigation

        // Go back to the previous page in the browser history
        driver.navigate().back();
        System.out.println("Navigated back to the previous page.");
        Thread.sleep(2000);

        // Re-switch to the iframe as context is lost after navigation
        driver.switchTo().frame(driver.findElement(iframe));
        System.out.println("Re-switched to iframe.");

        // Wait for the button to be clickable, then scroll and click
        WebElement viewAllCourses = wait.until(ExpectedConditions.elementToBeClickable(viewAllCoursesButton));
        js.executeScript("arguments[0].scrollIntoView(true);", viewAllCourses);
        System.out.println("Scrolled down to 'View All Courses' button.");
        Thread.sleep(1000);

        viewAllCourses.click();
        System.out.println("Clicked on 'View All Courses' button.");
        Thread.sleep(2000);

        // Switch back to the main page content
        driver.switchTo().defaultContent();
        System.out.println("Switched back to the main page.");
        Thread.sleep(1000);

        // Click on Home button on main page
        driver.findElement(homeButton).click();
        System.out.println("Clicked on 'Home' button on the main page.");
        Thread.sleep(1000);
    }
}
