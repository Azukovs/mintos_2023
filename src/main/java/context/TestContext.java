package context;

import org.openqa.selenium.WebDriver;
import vending.VendingMachine;

import java.util.EnumMap;

import static context.TestKey.VENDING_MACHINE;
import static context.TestKey.WEBDRIVER;

public class TestContext {
    private static final EnumMap<TestKey, Object> TEST_CONTEXT = new EnumMap<>(TestKey.class);

    public static void insertValue(TestKey key, Object value) {
        TEST_CONTEXT.put(key, value);
    }

    public static VendingMachine getVendingMachine() {
        return (VendingMachine) TEST_CONTEXT.get(VENDING_MACHINE);
    }

    public static WebDriver getWebDriver() {
        return (WebDriver) TEST_CONTEXT.get(WEBDRIVER);
    }

    public static void clearValue(TestKey key) {
        TEST_CONTEXT.remove(key);
    }
}
