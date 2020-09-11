Feature: Update Profile

  Background:

  Background:
    Given I authenticate using:
      | path        | email           | password |
      | /auth/login | bruno@email.com | bruno    |

  Scenario Outline: Update profile name
    When I update profile at uri "<path>" to name "<name>"
    Then I should see profile name as "<updatedName>"
    Examples:
      | path             | name | updatedName |
      | /posts/2/profile | Sam  | Sam         |