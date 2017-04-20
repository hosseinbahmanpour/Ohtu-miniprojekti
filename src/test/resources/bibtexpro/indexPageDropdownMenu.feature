Feature: As a user I can change inputform on indexPage

Scenario: page displays form to upload article-type reference on load
        Given index page is selected
        When  the page is loaded
        Then  input element with id "type" and value "Article" exists

Scenario: page can displays form to upload book-type reference 
        Given index page is selected
        When  "book" reference is selected
        Then  input element with id "type" and value "Book" exists

Scenario: page can displays form to upload proceeding-type reference 
        Given index page is selected
        When  "proceeding" reference is selected
        Then  input element with id "type" and value "Book" exists

Scenario: page does not displays other forms when article-reference is selected 
        Given index page is selected
        When  "article" reference is selected
        Then  input element contains only id "type" and value "Article"

Scenario: page does not displays other forms when book-reference is selected 
        Given index page is selected
        When  "book" reference is selected
        Then  input element contains only id "type" and value "Book"

Scenario: page does not displays other forms when proceeding-reference is selected 
        Given index page is selected
        When  "proceeding" reference is selected
        Then  input element contains only id "type" and value "Proceeding"