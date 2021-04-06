Feature: Search
  As a user
  I want to test search functionality

  Scenario Outline: Check search functionality
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User enters '<searchWord>' into search field
    And User checks Images tab visibility
    And User clicks on the Images tab
    Then User verifies that Images tab is opened by checking that url contains '<identifierWord>'

    Examples:
      | homePage                   | searchWord  | identifierWord  |
      | https://www.google.com     | Apple       |  isch           |

