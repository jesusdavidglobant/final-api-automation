# POC Based on mock service simulating a Transaction Service

# Update information from API
Feature: POC - Update actions


  # UPDATE Transaction by data table
  Scenario: Update Transaction based by id with data table information
    Given I get the response from the endpoint
    When I get the response code equals to 200
    Then I UPDATE the Transaction by id with information
      | name    | email                | active | id |
      | Hernan  | hernan_test@test.com | false  | 1  |