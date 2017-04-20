Feature: As a user I can select a reference type from a dropdown menu when adding a reference

Scenario: Select a Book-type reference from the dropdown menu
    Given index page is selected
    When "Book" reference is selected
    Then  input element with id "type" and value "Book" exists

Scenario: Select an Inproceeding-type reference from the dropdown menu
    Given index page is selected
    When "Inproceeding" reference is selected
    Then input element with id "type" and value "Inproceeding" exists

Scenario: Select an Article-type reference from the dropdown menu
    Given index page is selected
    When "Article" reference is selected
    Then input element with id "type" and value "Article" exists
