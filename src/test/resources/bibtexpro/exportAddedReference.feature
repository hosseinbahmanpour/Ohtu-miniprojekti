Feature: As a user I can export the added book references to a BibTeX file

  Scenario Outline: Export <type> reference to a BibTeX file
    Given <type> reference with the following attributes has been added: <attributes>
    When I export the BibTeX file
    Then a BibTeX file containing <type> with the following attributes should have been created: <attributes>

    Examples:
      | type    | attributes  |
      | a book  | refId: 42, title: PogChamp, author: Poggo Camp, year: 1984, publisher: Bobo |