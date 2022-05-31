# POC Based on mock service simulating a Transaction Service

# Create Transaction in the API
Feature: POC Example - Put actions

  # Create transactions based on a number of transactions
  Scenario: Create transactions based on a number of transactions
    Given I clean the endpoint
    When I create 9 new Transactions
    Then I get a total number of transactions equal to 9
