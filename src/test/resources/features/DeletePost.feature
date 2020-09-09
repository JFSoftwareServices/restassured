Feature: DeletePosts
  Test the delete operation

  Background:
    Given I ensure to Perform POST operation for "/posts" with body as
      | id | title              | author            |
      | 6  | API Testing course | ExecuteAutomation |

  @smoke
  Scenario: Verify DELETE operation after POST
    When  I Perform DELETE operation for "/posts/{postid}/"
      | postid |
      | 6      |
    And I perform GET operation with path parameter for "/posts/{postid}"
      | postid |
      | 6      |
    Then I "should not" see the body with title as "API Testing course"