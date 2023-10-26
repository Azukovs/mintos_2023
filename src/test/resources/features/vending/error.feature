@vending
Feature: Error handling

  In order to make sure vending machine is not used improperly,
  invalid scenarios must be handled and user informed accordingly

  Scenario: As a customer I cannot receive product without inserting enough money
    When I insert 0.80 money
    And I select and confirm product number 1
    Then an error message of "Not enough money, please add the remainder" is shown

  Scenario: As a customer I cannot receive product that is not available
    When I insert 1.50 money
    And I select product number 3
    Then an error message of "Selected product unavailable, please choose another" is shown

  Scenario: As a customer I cannot increase balance by providing an invalid coin
    When I insert an invalid coin
    Then an error message of "Coin not recognized" is shown
