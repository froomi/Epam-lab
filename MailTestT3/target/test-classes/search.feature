Feature: Mail
  As a user
  I want to test mail functionality


  Scenario: Check deleting message functionality
    Given User opens home page
    And User inputs login and presses Enter button
    And User inputs password and presses Enter button again
    When User clicks on first three messages' checkboxes
    And User gets amount of messages
    And User deletes pointed messages
    And User checks that messages were deleted
    Then User clicks on Undo button
    And User checks that messages were returned




#    Examples:
#      | homePage                                                                                 | login                   |   password        | email                   | message              |
#      | https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com  | fasye1632@gmail.com      |  testPassword12   | froomisecret@gmail.com  | hello, my alter ego! |

