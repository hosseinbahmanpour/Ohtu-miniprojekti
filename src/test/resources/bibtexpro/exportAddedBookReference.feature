Feature: As a user I can export the added book references to a BibTeX file

  Scenario: Export book reference to a BibTeX file
    Given there is a book in the reference library with the Id "42", Title "PogChamp", Author "Poggo Camp", year "1984", Publisher "Bobo"
    When I export the book reference
    Then a BibTeX file with the contents of Id "42", Title "PogChamp", Author "Poggo Camp", year "1984", Publisher "Bobo" should have been created
