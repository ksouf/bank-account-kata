Feature: In order to retrieve some or all of my savings
         As a bank client
         I want to make a withdrawal from my account

    Background:
      Given i have a bank account with balance 1000

  Scenario: i want to retrieve some of my saving;
    When i am withdrawing 500 from my account
    Then my balance must be 500


  Scenario: i want to retrieve some of my saving;
    When i am withdrawing 700 from my account
    Then my balance must be 300

  Scenario: i want to retrieve negative amount of my saving;
    When i am withdrawing -200 from my account
    Then my balance must be 1000

  Scenario: i want to retrieve more amount than my saving;
    When i am withdrawing 2000 from my account
    Then my balance must be 1000
