Feature: Update Profile

  Background: Authenticate
    Given I authenticate using:
      | path        | email           | password |
      | /auth/login | bruno@email.com | bruno    |

  Scenario Outline: Update profile name
    When I update profile at uri "<path>" to name "<name>"
    Then I should see profile name as "<updatedName>"
    Examples: Profile parameters
      | path             | name | updatedName |
      | /posts/2/profile | Sam  | Sam         |