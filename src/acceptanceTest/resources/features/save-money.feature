Feature: In order to save money
         As a bank client
         I want to make a deposit in my account

  Background:
   Given i have a bank account with balance 5000


  Scenario: i want to make deposit in my account;
     When i am depositing 2000 in my account
     Then my balance must be 7000

  Scenario: i want to make deposit in my account with another amount;
    When i am depositing 500 in my account
    Then my balance must be 5500

  Scenario: i want to make a negative deposit in my account with another amount;
    When i am depositing -500 in my account
    Then my balance must be 5000