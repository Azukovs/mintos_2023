package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import vending.Money;

import static context.TestContext.getVendingMachine;
import static java.lang.Double.parseDouble;
import static org.junit.Assert.*;

public class VendingSteps {
    @When("I insert {double} money")
    public void iInsertMoney(double money) {
        getVendingMachine().insertCoin(new Money(money));
    }

    @And("I select and confirm product number {int}")
    public void iSelectAndConfirmProductNumber(int productNumber) {
        iSelectProductNumber(productNumber);
        getVendingMachine().buy(productNumber);
    }

    @Then("I receive product number {int}")
    public void iReceiveProductNumber(int productNumber) {
        assertEquals(getVendingMachine().getOutputTrayProduct().getName(), getVendingMachine().getProducts()[productNumber - 1].getName());
    }

    @And("I select product number {int}")
    public void iSelectProductNumber(int productNumber) {
        getVendingMachine().selectProduct(productNumber);
    }

    @When("I choose to return money")
    public void iChooseToReturnMoney() {
        getVendingMachine().returnMoney();
    }

    @And("I receive remainder of {double}")
    public void iReceiveRemainderOf(double remainder) {
        assertEquals(getVendingMachine().getRemainderTray().asDouble(), remainder, 0.00);
    }

    @And("I confirm purchase of product number {int}")
    public void iConfirmPurchaseOfProductNumber(int productNumber) {
        getVendingMachine().buy(productNumber);
    }

    @Then("no products are provided")
    public void noProductsAreProvided() {
        assertNull(getVendingMachine().getOutputTrayProduct());
    }

    @Given("I have tried purchasing product number {int} with {double} money")
    public void iHaveTriedPurchasingProductNumberWithMoney(int productNumber, double money) {
        iInsertMoney(money);
        iSelectAndConfirmProductNumber(productNumber);
    }

    @Then("an error message of {string} is shown")
    public void anErrorMessageOfIsShown(String expectedError) {
        assertEquals(expectedError, getVendingMachine().getDisplay());
    }

    @When("I insert an invalid coin")
    public void iInsertAnInvalidCoin() {
        iInsertMoney(-1.5);
    }

    @Given("I have confirmed purchase of product number {int} without remainder")
    public void iHaveConfirmedPurchaseOfProductNumberWithoutRemainder(int productNumber) {
        iInsertMoney(getVendingMachine().getProducts()[productNumber - 1].getPrice().asDouble());
        iSelectAndConfirmProductNumber(productNumber);
    }

    @Given("vending machine has balance of {string}")
    public void vendingMachineHasBalanceOf(String startingBalance) {
        getVendingMachine().insertCoin(startingBalance);
    }

    @When("I insert {string} coin")
    public void iInsertCoin(String coinValue) {
        getVendingMachine().getAmount().add(coinValue);
    }

    @Then("current balance is updated to {string}")
    public void currentBalanceIsUpdatedTo(String expectedBalance) {
        assertEquals(getVendingMachine().getAmount().asDouble(), parseDouble(expectedBalance), 0.00);
    }
}
