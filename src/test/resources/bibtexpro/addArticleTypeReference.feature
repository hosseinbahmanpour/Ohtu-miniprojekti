Feature: As a user I can add an Article-type reference

Scenario: Add an article reference with the mandatory fields
    Given I have the required field available to insert the data into
    When I add an article reference with the Id "42", Author "Poggo Camp", Title "Pogs and Bogs", Journal "All things Bogling", year "1984", Volume "666"
    Then the reference "42", "Poggo Camp", "Pogs and Bogs", "All things Bogling", "1984", "666" should have been added
