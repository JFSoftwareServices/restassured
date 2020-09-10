Feature: Update Profile

  Background:
  Background:
    Given I authenticate using:
      | <uri>       | email           | password |
      | /auth/login | bruno@email.com | bruno    |

  Scenario Outline: Update profile name
    When I update profile at uri "<uri>" to name "<name>"
    Then I should see profile name as "<updatedName>"
    Examples:
      | uri              | name | updatedName |
      | /posts/2/profile | Sam  | Sam         |