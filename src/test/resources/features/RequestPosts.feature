Feature: Request Posts

  Scenario: Verify authors of the post
    Given I request for "/posts"
    Then I should see first two author name as "Karthik KK"

  Scenario: Verify authors of the post with params
    Given I request for "/posts" with pathParameters:
      | post | 1 |
    Then I should see author name as "Karthik KK"