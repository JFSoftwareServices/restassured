Feature: Update for author posts profile

  Background:
    Given I perform authentication operation for "/auth/login" with body
      | email           | password |
      | bruno@email.com | bruno    |

  Scenario Outline: Update profile name
    When I update profile at uri "<uri>" to name "<name>"
    Then I should see profile name as "<updatedName>"
    Examples:
      | uri              | name | updatedName |
      | /posts/2/profile | Sam  | Sam         |