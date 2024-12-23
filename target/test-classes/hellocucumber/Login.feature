Feature: Login

  @Ui
  Scenario: Login with a valid user
    Given a valid user
    When a valid email is inputted
    And a valid password is inputted
    And the user clicks the login button
    Then the user logs in

# A _best practice_ for scenario outline is the use of API testing instead of slow and tedious UI testing.
# Why? It's faster, lol.
  @Ui
  Scenario Outline: Login with many valid or invalid users
    Given a invalid user tries to log in
    When the user enters the wrong email as "<email>"
    And the user enters the wrong password as "<password>"
    And the user clicks the login button
    Then the user cannot log in

    Examples:
      | email                               | password         |
      | maximiliano.sosa.wideman@qubika.com | fakepassword.123 |
      | notarealmail@gmail.com              | 12345678.        |
#      |                                     | fakepassword.123 |
#      | maximiliano.sosa.wideman@qubika.com |                  |
#  in these two above steps, the authenticate button isn't even getting clickable so i disabled these examples

#  Defined and created a scenario just for the purpose of testing with APIs or Endpoints
  @API
  Scenario: Testing the login but with APIs
    Given a user wanting to log in by API
    When a valid email and password is passed to the login endpoint
    Then the user is logged in