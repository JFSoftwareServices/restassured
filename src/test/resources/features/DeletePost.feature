Feature: DeletePosts
  Test the delete operation

  Background:
    Given I perform authentication operation for "/auth/login" with body
      | email           | password |
      | bruno@email.com | bruno    |

  Scenario Outline: Create and then delete a Profile
    And I post to "<uri>" with "<title>" and "<author>" and <id>
    When I delete to "<uri>" with id <id>
    And I request for "<uri>" with id <id>
    Then I should not see a post

    Examples:
      | uri    | title       | author | id |
      | /posts | API Testing | Jide   | 10 |
      | /posts | C# Testing  | Ade    | 11 |