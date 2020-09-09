Feature: Create Profile

  Background:
    Given I perform authentication operation for "/auth/login" with body
      | email           | password |
      | bruno@email.com | bruno    |

  Scenario Outline: Create a Profile
    When I post to "<uri>" with "<name>" and <postId>
    Then I should see the new profile has name "<outputname>" and <outputpostId>

    Examples:
      | uri              | name | postId | outputname | outputpostId |
      | /posts/3/profile | Sams | 3      | Sams       | 3            |
      | /posts/4/profile | Tom  | 4      | Tom        | 4            |