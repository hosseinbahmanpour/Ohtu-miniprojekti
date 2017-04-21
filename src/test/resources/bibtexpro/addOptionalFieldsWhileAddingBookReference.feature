Feature: As a user I can add optional fieldss while adding a Book-type reference

Scenario: Add a book reference with the optional information of Volume/Number
    Given I have the required fields available to insert the data into
    When I add a book reference with the Id "42", Author/Editor "Poggo Camp", Title "Pogs and Bogs", Publisher "Bogling Publishing", year "1984", Volume/Number "13/37"
    Then the book "42", "Poggo Camp", "Pogs and Bogs", "Bogling Publishing", "1984", "13/37" should have been added

Scenario: Add a book reference with the optional information of Series
    Given I have the required fields available to insert the data into
    When I add a book reference with the Id "42", Author/Editor "Poggo Camp", Title "Pogs and Bogs", Publisher "Bogling Publishing", year "1984", Series "World series"
    Then the book "42", "Poggo Camp", "Pogs and Bogs", "Bogling Publishing", "1984", "World series" should have been added

Scenario: Add a book reference with the optional information of Address
    Given I have the required fields available to insert the data into
    When I add a book reference with the Id "42", Author/Editor "Poggo Camp", Title "Pogs and Bogs", Publisher "Bogling Publishing", year "1984", Address "myspace"
    Then the book "42", "Poggo Camp", "Pogs and Bogs", "Bogling Publishing", "1984", "myspace" should have been added

Scenario: Add a book reference with the optional information of Edition
    Given I have the required fields available to insert the data into
    When I add a book reference with the Id "42", Author/Editor "Poggo Camp", Title "Pogs and Bogs", Publisher "Bogling Publishing", year "1984", Edition "Collectors edition"
    Then the reference "42", "Poggo Camp", "Pogs and Bogs", "Bogling Publishing", "1984", "Collectors edition" should have been added

Scenario: Add a book reference with the optional information of Month
    Given I have the required fields available to insert the data into
    When I add a book reference with the Id "42", Author/Editor "Poggo Camp", Title "Pogs and Bogs", Publisher "Bogling Publishing", year "1984", Month "Banana"
    Then the reference "42", "Poggo Camp", "Pogs and Bogs", "Bogling Publishing", "1984", "Banana" should have been added

Scenario: Add a book reference with the optional information of Note
    Given I have the required fields available to insert the data into
    When I add a book reference with the Id "42", Author/Editor "Poggo Camp", Title "Pogs and Bogs", Publisher "Bogling Publishing", year "1984", Note "I forgot to take notes"
    Then the reference "42", "Poggo Camp", "Pogs and Bogs", "Bogling Publishing", "1984", "I forgot to take notes" should have been added

Scenario: Add a book reference with the optional information of Key
    Given I have the required fields available to insert the data into
    When I add a book reference with the Id "42", Author/Editor "Poggo Camp", Title "Pogs and Bogs", Publisher "Bogling Publishing", year "1984", Key "The magic key"
    Then the reference "42", "Poggo Camp", "Pogs and Bogs", "Bogling Publishing", "1984", "The magic key" should have been added
