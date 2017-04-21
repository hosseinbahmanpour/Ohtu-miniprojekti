Feature: As a user I can add an Inproceeding-type reference

Scenario: Add an Inproceeding reference with the mandatory fields
    Given I have the required fields available to insert the data into
    When I add an Inproceeding reference with the Id "42", Author "Poggo Camp", Title "Pogs and Bogs", Booktitle "Encyclopedia Boglinica", year "1984"
    Then the inproceeding "42", "Poggo Camp", "Pogs and Bogs", "Encyclopedia Boglinica", "1984" should have been added
