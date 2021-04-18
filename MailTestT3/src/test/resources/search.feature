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

