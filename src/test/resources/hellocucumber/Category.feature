Feature: Categories

  Scenario: Add a simple category
    Given a logged in user
    When they create a category
    And they write a valid name
    And they click the accept button
    Then the category creates and can be seen in the CategoryUI table
