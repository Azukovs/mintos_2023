package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import shopping.pages.Landing;

import static context.TestContext.getWebDriver;
import static shopping.pages.Article.addToCart;
import static shopping.pages.Article.selectRandomSize;
import static shopping.pages.Checkout.checkPaymentContainerExists;
import static shopping.pages.Checkout.inputMandatoryAddressData;
import static shopping.pages.Landing.checkoutCart;
import static shopping.pages.SearchResults.openArticle;
import static shopping.pages.SignIn.inputMandatoryUserData;
import static shopping.pages.SignIn.openJoinTab;

public class ShoppingSteps {
    @Given("I am on page {string}")
    public void iAmOnPage(String homePage) {
        getWebDriver().get(homePage);
    }

    @And("I search for keyword {string}")
    public void iSearchForKeyword(String keyword) {
        Landing.searchForItem(keyword);
    }

    @And("I add product number {int} of random size to cart")
    public void iAddProductNumberOfRandomSizeToCart(int productNumber) {
        openArticle(productNumber);
        selectRandomSize();
        addToCart();
    }

    @When("I checkout my current cart")
    public void iCheckoutMyCurrentCart() {
        checkoutCart();
    }

    @And("I input mandatory user form data")
    public void iInputMandatoryUserFormData() {
        openJoinTab();
        inputMandatoryUserData();
    }

    @And("I input mandatory address form data")
    public void iInputMandatoryAddressFormData() {
        inputMandatoryAddressData();
    }

    @Then("Option to add credit card is shown")
    public void optionToAddCreditCardIsShown() {
        checkPaymentContainerExists();
    }
}
