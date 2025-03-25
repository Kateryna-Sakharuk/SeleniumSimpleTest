Feature: Search for a product on Amazon as an anonymous user

  Scenario Outline: Anonymous user searches for a product
    Given Anonymous user opens the Amazon home page
    When User searches for a product with the parameter '<product>'
    And User retrieves the list of product titles from the search results
    Then The search results should not be empty

    Examples:
      | product |
      | watch   |


Feature: Search for a product on Amazon as an authorized user

  Scenario Outline: Authorized user searches for a product
    Given Anonymous user opens the Amazon home page
    When Anonymous user clicks on the Sign In button
    And User logs in with email '<email>' and password '<password>'
    And User searches for a product with the parameter '<product>'
    And User retrieves the list of product titles from the search results
    Then The search results should not be empty

    Examples:
      |email                   |password  |product   |
      |katyasaharuk18@gmail.com|1234Qwer  |watch     |


Feature: Shopping List Functionality

  Scenario Outline: Add a searched product to the shopping list
    Given Anonymous user opens the Amazon home page
    When Anonymous user clicks on the Sign In button
    And User logs in with email '<email>' and password '<password>'
    And User searches for a product with the parameter '<product>'
    Then The search results should not be empty

    When User clicks on the first product in the search results
    And Authorized user adds the product to the shopping list
    And Save the first product's name to the test cache
    And Authorized user clicks on the 'View Your List' button
    Then The first product added to the shopping list should be match the expected product

    Examples:
      |email                   |password  |product   |
      |katyasaharuk18@gmail.com|1234Qwer  |watch     |