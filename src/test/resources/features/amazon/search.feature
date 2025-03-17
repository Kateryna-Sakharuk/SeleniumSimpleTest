Feature: Search for a product on Amazon as an anonymous user

  Scenario: Anonymous user searches for a product
    Given Anonymous user opens the Amazon home page
    When User searches for a product with the parameter "watch"
    Then User retrieves the list of product titles from the search results
    Then The search results should not be empty

Feature: Search for a product on Amazon as an authorized user

  Scenario: Authorized user searches for a product
    Given Anonymous user opens the Amazon home page
    When Anonymous user clicks on the Sign In button
    When User logs in with email "katyasaharuk18@gmail.com" and password "1234Qwer"
    When User searches for a product with the parameter "watch"
    When User retrieves the list of product titles from the search results
    Then The search results should not be empty

Feature: Shopping List Functionality

  Scenario: Add a searched product to the shopping list
    Given Anonymous user opens the Amazon home page
    When Anonymous user clicks on the Sign In button
    When User logs in with email "katyasaharuk18@gmail.com" and password "1234Qwer"
    When User searches for a product with the parameter "watch"
    Then The search results should not be empty
    When User clicks on the first product in the search results
    When Authorized user adds the product to the shopping list
    When Save the first product's name to the test cache
    When Authorized user clicks on the 'View Your List' button
    Then The first product added to the shopping list should be match the expected product
