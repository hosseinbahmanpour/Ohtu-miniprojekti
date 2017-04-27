Feature: As a user I can add a book reference without scandics and with limited fields

  Scenario: Add a book reference without scandics
    Given I have the required fields available to insert the data into
    When I add a book reference with the Id "42", Title "PogChamp", Author "Poggo Camp", year "1984",Publisher "Bobo"
    Then the book "42", "PogChamp", "Poggo Camp", "1984", "Bobo" should have been added
