Feature: As a user I can add a valid reference

Scenario Outline: Adding <type> reference
    Given I have a form for adding <type> available
    When I add <type> reference with the following attributes: <attributes>
    Then <type> with the following attributes should have been added: <attributes>
    
    Examples:
        | type              | attributes    |
        | an article        | refId: 42, author: Poggo Camp, title: Pogs and Bogs, journal: All things Bogling, year: 1984, volume: 666 |
        | a book            | refId: 42, author: Poggo Camp, title: PogChamp, publisher: Bobo, year: 1984 |
        | an inproceedings  | refId: 42, author: Poggo Camp, title: Pogs and Bogs, booktitle: Encyclopedia Boglinica, year: 1984 |