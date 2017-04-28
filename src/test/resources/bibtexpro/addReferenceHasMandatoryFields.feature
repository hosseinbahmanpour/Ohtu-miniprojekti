Feature: As a user I am required to fill out the mandatory fields
        
Scenario Outline: Attempting to add <type> reference with mandatory fields left empty
    Given I have a form for adding <type> available
    When I add <type> reference with the following attributes: <attributes>
    Then <type> with the following attributes should not have been added: <attributes>
    
    Examples:
        | type              | attributes    |
        | an article        | author: aiai, title: huhhuh, journal: ei, year: 3000, volume: 20 |
        | an article        | refId: 10, title: huhhuh, journal: ei, year: 3000, volume: 20 |
        | an article        | refId: 10, author: aiai, journal: ei, year: 3000, volume: 20 |
        | an article        | refId: 10, author: aiai, title: huhhuh, year: 3000, volume: 20 |
        | an article        | refId: 10, author: aiai, title: huhhuh, journal: ei, volume: 20 |
        | an article        | refId: 10, author: aiai, title: huhhuh, journal: ei, year: 3000 |
        | a book            | author: Ceko, title: Arrgh, publisher: Cheek Publishing, year: 2000 |
        | a book            | refId: 43, title: Arrgh, publisher: Cheek Publishing, year: 2000 |
        | a book            | refId: 43, author: Ceko, publisher: Cheek Publishing, year: 2000 |
        | a book            | refId: 43, author: Ceko, title: Arrgh, year: 2000 |
        | a book            | refId: 43, author: Ceko, title: Arrgh, publisher: Cheek Publishing |
        | an inproceedings   | author: waza, title: waza waza, booktitle: waza waza waza, year: 900 |
        | an inproceedings   | refId: 20, title: waza waza, booktitle: waza waza waza, year: 900 |
        | an inproceedings   | refId: 20, author: waza, booktitle: waza waza waza, year: 900 |
        | an inproceedings   | refId: 20, author: waza, title: waza waza, year: 900 |
        | an inproceedings   | refId: 20, author: waza, title: waza waza, booktitle: waza waza waza |