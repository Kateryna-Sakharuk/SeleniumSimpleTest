Feature: Amazon Login

  Scenario Outline: User successfully logs into Amazon
    Given Anonymous user opens the Amazon home page
    When Anonymous user clicks on the Sign In button
    When User logs in with email '<email>' and password '<password>'
    Then The Amazon header logo should be displayed
    And The homepage main body should be visible
    And The account name should contain '<user name>'


    Examples:
    |email                   |password  |user name    |
    |katyasaharuk18@gmail.com|1234Qwer  |Hello, Katya |