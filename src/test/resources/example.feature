Feature: Examples of Using Step Def Table AddOns

  Scenario: Step Def Table
    Given table is
    | Name 1 | Name 2 |
    | Sam | John |

    Scenario: Read CSV File
      Given CSV file is
      | c:\Users\KenV1\IdeaProjects\CucumberStepDefTableAddon\resources\Example.csv |
      When CSV file is read
      Then data matches
      | Name 1 |Name 2|
    | Sam    | John |
    |Mary    |Jane  |

      Scenario: Step Def Table with Types
        Given typed table is
        | A String | A Number | A MyClass |
        | abc      | 1      | abc     |


