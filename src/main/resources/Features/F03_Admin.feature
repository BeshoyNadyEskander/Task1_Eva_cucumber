@smoke @test

Feature: F03_Admin | users could use Admin functionality for adding new employee

  Scenario: verify on user could add new employee and recorded is increased by 1

    Given admin navigate Login Page
    When admin enter valid username
    And admin enter valid password
    And admin click on login button
    And admin click on Admin button
    And Get the number of records found
    And admin could click on add button
    And Fill the required data
    Then Verify that the number of records increased by one


  Scenario: verify on user could delete new added employee and recorded is decreased by 1

    Given admin navigate Login Page
    And admin enter valid username
    And admin enter valid password
    And admin click on login button
    And admin click on Admin button
    And admin could click on add button
    And Fill the required data
    When search on new added username
    Then Verify that the number of records decreased by one
