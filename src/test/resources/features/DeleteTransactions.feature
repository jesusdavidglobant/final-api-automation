# POC Based on mocking a Transaction Service

# Delete data from the API
Feature: POC - Delete actions

  # DELETE Request made to leave empty the endpoint
  # Max DELETE requests allowed 9
  # Max 9 Transactions can be deleted
  Scenario: Delete all the transactions
    Given I clean the endpoint
    When I get the response code equals to 200
    Then I verify the endpoint is empty