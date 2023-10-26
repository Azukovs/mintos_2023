package shopping.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static context.TestContext.getWebDriver;
import static org.openqa.selenium.By.xpath;

public class SearchResults {
    public static final String LISTINGS = "//article";

    public static List<WebElement> getResults() {
        return getWebDriver().findElements(xpath(LISTINGS));
    }

    public static void openArticle(int articleNumber) {
        getResults().get(articleNumber).findElement(By.xpath(".//a")).click();
    }
}
