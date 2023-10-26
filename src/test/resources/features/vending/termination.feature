@vending
Feature: Termination of shopping flow

  In order to for customer to be able to change their mind during purchase process,
  vending machine must allow customer to terminate the process at any time and retrieve their money

  Scenario: As a customer I can stop purchase after inserting money
    Given I insert 0.20 money
    When I choose to return money
    Then no products are provided
    And I receive remainder of 0.20

  Scenario: As a customer I can stop purchase after choosing product
    Given I insert 0.50 money
    And I select product number 1
    When I choose to return money
    Then no products are provided
    And I receive remainder of 0.50

  Scenario: As a customer I can stop purchase after changing chosen product
    Given I insert 0.10 money
    And I select product number 1
    And I select product number 2
    When I choose to return money
    Then no products are provided
    And I receive remainder of 0.10
