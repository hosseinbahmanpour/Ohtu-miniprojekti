Feature: As a user I am required to fill out the mandatory fields when adding a Book-type reference

Scenario: Add a book reference with the mandatory fields
    Given I have the required fields available to insert the data into
    When I add a book reference with the Id "42", Author/Editor "Poggo Camp", Title "Pogs and Bogs", Publisher "Bogling Publishing", year "1984"
    Then the book "42", "Poggo Camp", "Pogs and Bogs", "Bogling Publishing", "1984" should have been added

Scenario: Add a book reference with mandatory fields left empty
    Given I have the required fields available to insert the data into
    When I add a book reference with the Id "43", Title "Arrgh", Publisher "Cheek Publishing", year "2000"
    Then the book "43", "Arrgh", "Cheek Publishing", "2000" should not have been added
