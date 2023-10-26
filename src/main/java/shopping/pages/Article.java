package shopping.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static context.TestContext.getWebDriver;
import static org.openqa.selenium.By.xpath;

public class Article {
    public static final String SIZE_SELECT = "//select[@id='variantSelector']";
    public static final String ADD_TO_CART = "//button[@aria-label='Add to bag']";

    public static void selectRandomSize() {
        List<WebElement> options = getWebDriver().findElement(xpath(SIZE_SELECT)).findElements(By.xpath(".//option[@data-testid]"));
        options.stream().findAny().orElseThrow().click();
    }

    public static void addToCart() {
        getWebDriver().findElement(xpath(ADD_TO_CART)).click();
    }
}
