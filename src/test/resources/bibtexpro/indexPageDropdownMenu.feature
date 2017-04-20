Feature: As a user I can change inputform on indexPage

Scenario: page displays form to upload article-type reference on load
        Given index page is selected
        When  the page is loaded
        Then  input element with id "type" and value "Article" exists