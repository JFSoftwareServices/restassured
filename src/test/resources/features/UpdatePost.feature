Feature: Update Post

  Background:
    Given I authenticate using:
      | <uri>       | email           | password |
      | /auth/login | bruno@email.com | bruno    |

  Scenario Outline: Create and then update a Post
    And I create a post using "<uri>" with "<title>" and "<author>" and <id>
    When I update post title to "<updatedTitle>" using "<uri>" and id <id>
    And I request for a post with "<uri>" and id <id>
    Then The title of the post should be "<updatedTitle>"

    Examples:
      | uri    | title       | updatedTitle       | author | id |
      | /posts | API Testing | API Testing course | Jide   | 10 |
      | /posts | C# Testing  | C# Testing course  | Ade    | 11 |