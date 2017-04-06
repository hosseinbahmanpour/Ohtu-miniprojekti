Feature: As a user I can add a book reference without scandics and with limited fields

  Scenario: Add a book reference without scandics
    Given I have the required fields avaialble to insert the data into
    When I add a book reference with the Id "42", Title "PogChamp", Author "Poggo Camp", year "1984"
    Then the reference "42", "PogChamp", "Poggo Camp", "1984" should have been added
