Feature: As a user I can see informational notifications when needed

Scenario Outline: Get notifications from a succesful addition
    Given the add page is selected
    When I add <type> reference with the following attributes: <attributes>
    Then I get notification from a succesful addition: <attributes>

Examples:
        | type              | attributes | 
        | an article        | refId: 42, author: Poggo Camp, title: Pogs and Bogs, journal: All things Bogling, year: 1984, volume: 666 |

Scenario Outline: Get notifications from a succesful removal
    Given <type> reference exists in the database with the following attributes: <attributes>
    When I remove <type> reference with the following attributes: <attributes>
    Then I get notification from a succesful removal: <attributes>

Examples:
        | type              | attributes | 
        | an article        | refId: 42, author: Poggo Camp, title: Pogs and Bogs, journal: All things Bogling, year: 1984, volume: 666 |
