Feature: Search for a product on Amazon as an anonymous user

  Scenario: Anonymous user searches for a product
    Given Anonymous user opens the Amazon home page
    When User searches for a product with the parameter "watch"
    Then User retrieves the list of product titles from the search results
    Then The search results should not be empty