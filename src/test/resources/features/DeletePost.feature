Feature: Delete Post

  Background:
    Given I authenticate using:
      | <uri>       | email           | password |
      | /auth/login | bruno@email.com | bruno    |

  Scenario Outline: Create and then delete a Post
    And I create a post using "<uri>" with "<title>" and "<author>" and <id>
    When I delete a post using "<uri>" with id <id>
    And I request for a post with "<uri>" and id <id>
    Then I should not see a post

    Examples:
      | uri    | title       | author | id |
      | /posts | API Testing | Jide   | 10 |
      | /posts | C# Testing  | Ade    | 11 |