package shopping.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static context.TestContext.getWebDriver;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class Landing {
    public static final String SEARCH_FIELD = "//input[@type='search']";
    public static final String SEARCH_SUBMIT = "//button[@type='submit']";
    public static final String CART = "//*[@id='miniBagDropdown']";
    public static final String CART_CHECKOUT = "//a[@data-test-id='checkout-link']";

    public static void searchForItem(String input) {
        getWebDriver().findElement(xpath(SEARCH_FIELD)).sendKeys(input);
        getWebDriver().findElement(xpath(SEARCH_SUBMIT)).click();
    }

    public static void checkoutCart() {
        WebDriver driver = getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(5));
        Actions hoverAction = new Actions(driver).moveToElement(driver.findElement(xpath(CART)));
        wait.until(elementToBeClickable(driver.findElement(xpath(CART))));
        hoverAction.build().perform();

        wait.until(elementToBeClickable(driver.findElement(xpath(CART_CHECKOUT))));
        driver.findElement(xpath(CART_CHECKOUT)).click();
    }
}
