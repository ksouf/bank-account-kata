Feature: In order to check my operations
         As a bank client
         I want to see the history (operation, date, amount, balance) of my operations

 Background:
    Given i have a bank account with balance with this statement
     | operation | date       | amount | balance |
     | DEPOSIT   | 2016-05-05 |  600   | 8400    |
     | WITHDRAW  | 2016-05-07 |  4000  | 9000    |


  Scenario: checking the history;
    When i print my statement i got this output
    """
      | operation | date       | amount | balance |
      | DEPOSIT   | 05/05/2016 |  600   | 8400    |
      | WITHDRAW  | 07/05/2016 |  4000  | 9000    |
    """


  Scenario: executing some valid operations and checking the history;
    Given i am withdrawing 500 from my account
    And i am depositing 2000 in my account
    When i print my statement i got this output
    """
      | operation | date       | amount | balance |
      | DEPOSIT   | 05/05/2016 |  600   | 8400    |
      | WITHDRAW  | 07/05/2016 |  4000  | 9000    |
      | WITHDRAW  | 07/05/2016 |  500   | 5000    |
      | DEPOSIT   | 07/05/2016 |  2000  | 4500    |
    """

  Scenario: executing some invalid operations and checking the history;
    Given i am withdrawing 7000 from my account
    And i am depositing -2000 in my account
    When i print my statement i got this output
    """
      | operation | date       | amount | balance |
      | DEPOSIT   | 05/05/2016 |  600   | 8400    |
      | WITHDRAW  | 07/05/2016 |  4000  | 9000    |
    """