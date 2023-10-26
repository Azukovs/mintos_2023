@vending
Feature: Core functionality

  In order for vending machine to function as expected,
  these base scenarios must be working as expected

  Scenario: As a customer I want to be able to purchase a product
    When I insert 1.50 money
    And I select and confirm product number 1
    Then I receive product number 1

  Scenario Outline: As a customer I want to be able to see the current balance
    Given vending machine has balance of "<current_balance>"
    When I insert "<value>" coin
    Then current balance is updated to "<new_balance>"

    Examples:
      | value | current_balance | new_balance |
      | 0.05  | 0.00            | 0.05        |
      | 0.10  | 0.00            | 0.10        |
      | 0.20  | 0.00            | 0.20        |
      | 0.50  | 0.00            | 0.50        |
      | 1.00  | 0.00            | 1.00        |
      | 2.00  | 0.00            | 2.00        |
      | 0.05  | 0.25            | 0.30        |
      | 0.20  | 0.30            | 0.50        |
      | 0.50  | 1.70            | 2.20        |

  Scenario: As a customer I want to be able to retrieve remainder
    When I insert 1.50 money
    And I select and confirm product number 1
    Then I receive product number 1
    And I receive remainder of 0.50

  Scenario: As a customer I want to be able to change selected product
    When I insert 2.00 money
    And I select product number 1
    And I select product number 2
    And I confirm purchase of product number 2
    Then I receive product number 2

  Scenario: As a customer I can add money if I initially did not insert enough
    Given I have tried purchasing product number 2 with 1.50 money
    And an error message of "Not enough money, please add the remainder" is shown
    When I insert 0.50 money
    And I confirm purchase of product number 2
    Then I receive product number 2

  Scenario: As a customer I cannot retrieve money after purchase is completed
    Given I have confirmed purchase of product number 1 without remainder
    When I choose to return money
    Then I receive remainder of 0.00
