Feature: As a user I can add optional attributes while adding a Book-type reference

Scenario Outline: Add a <type> reference with optional attributes
    Given I have a form for adding <type> available
    When I add <type> reference with the following attributes: <attributes>
    Then <type> with the following attributes should have been added: <attributes>
    
    Examples:
        | type              | attributes |
        | an article        | refId: 99, author: zxc, title: qaz, journal: xsw, year: 543, volume: 11, number: 222, pages: 33 |
        | an article        | refId: 55, author: cxz, title: zaq, journal: wsx, year: 345, volume: 88, month: dec, note: aku, key: apu |
        | a book            | refId: 42, author: vfr, title: jmu, publisher: gbp, year: 919, volume: 1, series: ses, address: bury|
        | a book            | refId: 24, author: rfv, title: muj, publisher: gpb, year: 191, edition: new, month: jun, note: nbv, key: akj |
        | an inproceedings  | refId: 00, author: yhn, title: iop, booktitle: rty, year: 313, editor: rdf, volume: eas, series: ded, pages: 191, address: xax |
        | an inproceedings  | refId: 44, author: nhy, title: poi, booktitle: ytr, year: 131, month: ssd, organization: wpl, publisher: qkl, note: avf, key: zaz |