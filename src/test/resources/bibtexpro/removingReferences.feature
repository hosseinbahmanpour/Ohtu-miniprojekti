Feature: As a user I can remove references

Scenario Outline: Removing a <type> reference
    Given <type> reference exists in the database with the following attributes: <attributes>
    When I remove <type> reference with the following attributes: <attributes>
    Then <type> with the following attributes should not exist: <attributes>
    
    Examples:
        | type              | attributes    |
        | an article        | refId: 42, author: Poggo Camp, title: Pogs and Bogs, journal: All things Bogling, year: 1984, volume: 666 |
        | a book            | refId: 42, author: Poggo Camp, title: PogChamp, publisher: Bobo, year: 1984 |
        | an inproceedings  | refId: 42, author: Poggo Camp, title: Pogs and Bogs, booktitle: Encyclopedia Boglinica, year: 1984 |