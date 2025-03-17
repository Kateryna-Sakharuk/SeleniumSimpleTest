Feature: Amazon Login

  Scenario: User successfully logs into Amazon
    Given Anonymous user opens the Amazon home page
    When Anonymous user clicks on the Sign In button
    When User logs in with email "katyasaharuk18@gmail.com" and password "1234Qwer"
    Then The Amazon header logo should be displayed
    And The homepage main body should be visible
    And The account name should contain "Hello, Katya"