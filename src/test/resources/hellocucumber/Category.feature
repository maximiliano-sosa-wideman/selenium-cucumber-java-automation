Feature: Categories

  Scenario: Add a simple category
    Given a logged in user
    When they create a category
    Then the category can be seen in the Category table
