Feature: Update Post

  Background: Authenticate
    Given I authenticate using:
      | path        | email           | password |
      | /auth/login | bruno@email.com | bruno    |

  Scenario Outline: Create and then update a Post
    And I create a post using "<path>", "<title>", "<author>", <id>
    When I update post title to "<updatedTitle>" using "<path>" and id <id>
    And I request for a post with "<path>" and id <id>
    Then The title of the post should be "<updatedTitle>"

    Examples: Post parameters
      | path   | title       | updatedTitle       | author | id |
      | /posts | API Testing | API Testing course | Jide   | 10 |
      | /posts | C# Testing  | C# Testing course  | Ade    | 11 |