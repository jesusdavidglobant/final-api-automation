# POC Based on mock service simulating a Transaction Service

# Get information from API
Feature: POC - Get actions
  # Get request scenario to verify none-repeated emails
  Scenario: Get transactions to verify none-repeated emails
    Given I create 2 new Transactions
    When I get the response code equals to 200
    Then I verify not repeated emails