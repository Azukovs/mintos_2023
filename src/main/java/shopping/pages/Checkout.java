package shopping.pages;

import com.mifmif.common.regex.Generex;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static context.TestContext.getWebDriver;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class Checkout {
    //checkout
    public static final String MOBILE = "//input[@id='contactNumber']";
    public static final String ADDRESS = "//input[@id='address1']";
    public static final String CITY = "//input[@id='locality']";
    public static final String POSTCODE = "//input[@id='postalCode']";
    public static final String DELIVER_BUTTON = "//button[@type='submit']";
    public static final String ADD_CARD_BUTTON = "//button[@aria-label='Credit/debit card']";
    public static final String PAYMENT_CONTAINER = "//div[@class='payment-details']";

    public static void inputMandatoryAddressData() {
        Generex numberGenerator = new Generex("[0-9]");
        Generex stringGenerator = new Generex("[a-z]");
        WebDriver driver = getWebDriver();

        driver.findElement(xpath(MOBILE)).sendKeys(valueOf(numberGenerator.random(8, 8)));
        driver.findElement(xpath(ADDRESS)).sendKeys(format("%s street", stringGenerator.random(5, 10)));
        driver.findElement(xpath(CITY)).sendKeys(format("%s", stringGenerator.random(5, 10)));
        driver.findElement(xpath(POSTCODE)).sendKeys(numberGenerator.random(5, 5));
        driver.findElement(xpath(DELIVER_BUTTON)).click();

        new WebDriverWait(driver, ofSeconds(5)).until(elementToBeClickable(driver.findElement(xpath(ADD_CARD_BUTTON))));
        driver.findElement(xpath(ADD_CARD_BUTTON)).click();
    }

    public static void checkPaymentContainerExists() {
        assertTrue(getWebDriver().findElement(xpath(PAYMENT_CONTAINER)).isDisplayed());
    }
}
