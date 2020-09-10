Feature: Create Profile

  Background:
    Given I authenticate using:
      | <uri>       | email           | password |
      | /auth/login | bruno@email.com | bruno    |

  Scenario Outline: Create a Profile
    When I create a profile using "<uri>" with "<name>" and <postId>
    Then I should see the new profile has name "<name>" and <postId>

    Examples:
      | uri              | name | postId |
      | /posts/3/profile | Sams | 3      |
      | /posts/4/profile | Tom  | 4      |