Feature: Use login
  As a valid user I should be able to
  login to the system with valid credentials
  and view product inventory page

  Scenario: Login with valid credentials
    Given user is in the login page
    When user provide valid credentials
    Then the user should be redirected to the product inventory page

    Scenario:
      Given user has accessed the login page
      When user provide invalid credentials "standard_user", "invalid"
      Then the user should be see error message"Epic sadface: Username and password do not match any user in this service"





