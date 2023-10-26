package shopping.pages;

import com.mifmif.common.regex.Generex;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static context.TestContext.getWebDriver;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;
import static java.time.LocalDateTime.now;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class SignIn {
    public static final String JOIN_TAB = "//a[@id='new-to-asos-tab']";
    public static final String EMAIL_FIELD = "//input[@id='Email']";
    public static final String FIRST_NAME_FIELD = "//input[@id='FirstName']";
    public static final String LAST_NAME_FIELD = "//input[@id='LastName']";
    public static final String PASSWORD_FIELD = "//input[@id='Password']";
    public static final String BIRTH_DAY_DROPDOWN = "//select[@id='BirthDay']";
    public static final String BIRTH_MONTH_DROPDOWN = "//select[@id='BirthMonth']";
    public static final String BIRTH_YEAR_DROPDOWN = "//select[@id='BirthYear']";
    public static final String SUBMIT_FORM = "//input[@id='register']";
    public static final String NOT_A_ROBOT = "//*[@aria-label='I am not a robot']";

    public static void openJoinTab() {
        getWebDriver().findElement(xpath(JOIN_TAB)).click();
    }

    public static void inputMandatoryUserData() {
        Generex numberGenerator = new Generex("[0-9]");
        Generex stringGenerator = new Generex("[a-z]");
        WebDriver driver = getWebDriver();

        driver.findElement(xpath(EMAIL_FIELD)).sendKeys(format("test_%s@somemail.com", numberGenerator.random(10,10)));
        driver.findElement(xpath(FIRST_NAME_FIELD)).sendKeys(format("John%s", stringGenerator.random(10,10)));
        driver.findElement(xpath(LAST_NAME_FIELD)).sendKeys(format("Doe%s", stringGenerator.random(10,10)));
        driver.findElement(xpath(PASSWORD_FIELD)).sendKeys(format("%s%s", stringGenerator.random(6,6), numberGenerator.random(6,6)));
        driver.findElement(xpath(BIRTH_DAY_DROPDOWN)).findElements(xpath(".//option[text()=number()]"))
                .stream().findAny().orElseThrow().click();
        driver.findElement(xpath(BIRTH_MONTH_DROPDOWN)).findElements(xpath(".//option[not(text()='Month')]"))
                .stream().findAny().orElseThrow().click();
        driver.findElement(xpath(BIRTH_YEAR_DROPDOWN)).findElements(xpath(".//option[text()=number()]"))
                .stream()
                .filter(element -> parseInt(element.getText()) < (now().getYear() - 17))    //16 or over, lazy check
                .findAny().orElseThrow().click();
        driver.findElement(xpath(SUBMIT_FORM)).click();

        new WebDriverWait(driver, ofSeconds(5)).until(elementToBeClickable(driver.findElement(xpath(NOT_A_ROBOT))));
        driver.findElement(xpath(NOT_A_ROBOT)).click();
    }

}
