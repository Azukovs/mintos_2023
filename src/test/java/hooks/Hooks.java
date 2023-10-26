package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import vending.Money;
import vending.Product;
import vending.VendingMachine;

import static context.TestContext.*;
import static context.TestKey.VENDING_MACHINE;
import static context.TestKey.WEBDRIVER;

public class Hooks {

    @Before("@vending")
    public void vendingMachineInitialization() {
        System.out.println("Initialization before vending tests");
        VendingMachine machine = new VendingMachine("Generic Manufacturer");
        Product p1 = new Product("Generic Water Bottle", new Money(1, 0), 10);
        Product p2 = new Product("Generic Soda Bottle", new Money(2, 0), 10);
        Product p3 = new Product("Unavailable Water Bottle", new Money(0, 50), 0);
        machine.setProducts(new Product[]{p1, p2, p3});
        insertValue(VENDING_MACHINE, machine);
    }

    @After("@vending")
    public void cleanup() {
        System.out.println("Cleanup after vending tests");
        clearValue(VENDING_MACHINE);
    }

    @Before("@shopping")
    public void initializeWebDriver() {
//        WebDriver driver = new ChromeDriver();
//        WebDriver driver = new FirefoxDriver();

        WebDriver driver = new EdgeDriver();
        insertValue(WEBDRIVER, driver);
    }

    @After("@shopping")
    public void driverCleanup() {
        getWebDriver().close();
        clearValue(WEBDRIVER);
    }
}
