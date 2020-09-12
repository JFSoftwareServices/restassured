Feature: Create Profile

  Background: Authenticate
    Given I authenticate using:
      | path        | email           | password |
      | /auth/login | bruno@email.com | bruno    |

  Scenario Outline: Create a Profile
    When I create a profile using "<path>", "<postIdKey>", <postIdValue>, "<name>"
    Then the new profile has name "<name>" and postId <postIdValue>

    Examples: Profile parameters
      | path                    | postIdKey | postIdValue | name |
      | /posts/{postId}/profile | postId    | 3           | Sams |
      | /posts/{postId}/profile | postId    | 4           | Tom  |