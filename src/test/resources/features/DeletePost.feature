Feature: Delete Post

  Background:
    Given I authenticate using:
      | path        | email           | password |
      | /auth/login | bruno@email.com | bruno    |

  Scenario Outline: Delete a Post
    And I create a post using "<path>", "<title>", "<author>", <id>
    When I delete a post using "<path>" with id <id>
    And I request for a post with "<path>" and id <id>
    Then I should not see a post

    Examples:
      | path   | title       | author | id |
      | /posts | API Testing | Jide   | 10 |
      | /posts | C# Testing  | Ade    | 11 |