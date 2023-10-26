@shopping
Feature: Basic test to purchase items until payment

  Scenario: As a customer I want ot be able to put multiple items in cart and purchase
    Given I am on page "https://www.asos.com/"
    And I search for keyword "mintos"
    And I add product number 1 of random size to cart
    And I search for keyword "mintos"
    And I add product number 3 of random size to cart
    When I checkout my current cart
    And I input mandatory user form data
    And I input mandatory address form data
    Then Option to add credit card is shown

