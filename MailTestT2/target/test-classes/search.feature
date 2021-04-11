Feature: Mail
  As a user
  I want to test mail functionality

  Scenario Outline: Check send message functionality
    Given User opens '<homePage>' page
    And User inputs '<login>' and presses Enter button
    And User inputs '<password>' and presses Enter button again
    And User clicks on Compose button
    When User inputs another person email '<email>' account as a message receiver
    And User fills in message form with some '<message>'
    And User clicks on send button
    And User checks that message was sent
    Then User verifies that message was sent to the receiver by clicking on sent button
    And User checks that last message was sent right to '<email>'

    Examples:
      | homePage                                                                                 | login                   |   password        | email                   | message              |
      | https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com  | fasye1632@gmail.com      |  testPassword12   | froomisecret@gmail.com  | hello, my alter ego! |

