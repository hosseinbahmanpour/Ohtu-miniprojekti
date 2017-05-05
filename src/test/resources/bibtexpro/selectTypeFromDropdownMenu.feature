Feature: As a user I can select a reference type from a dropdown menu when adding a reference

Scenario Outline: Select a Book-type reference from the dropdown menu
    Given the add page is selected
    When <type> reference is selected
    Then the following input fields should be visible: <fields>
    
    Examples:
        | type              | fields    |
        | an article        | author, title, journal, year, volume, number, pages, month, note, key |
        | a book            | author, title, publisher, year, volume, series, address, edition, month, note, key |
        | an inproceedings   | author, title, booktitle, year, editor, volume, series, pages, address, month, organization, publisher, note, key |