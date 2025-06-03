@smoke

Feature: F02_DashBoard | users could use DashBoard functionality

  Scenario: user could choose admin from Menu

    Given admin navigate Login Page
    When admin enter valid username
    And admin enter valid password
    And admin click on login button
    And admin click on Admin button