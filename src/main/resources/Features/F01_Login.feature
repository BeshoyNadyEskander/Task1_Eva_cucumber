@smoke
Feature: F01_Login | users could use login functionality to redirect his account

  Scenario: Admin could login with valid Email and password

    Given admin navigate Login Page
    When admin enter valid username
    And admin enter valid password
    And admin click on login button
    Then verify admin login successfully
