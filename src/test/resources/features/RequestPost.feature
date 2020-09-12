Feature: Request Post

  Background: Authenticate
    Given I authenticate using:
      | path        | email           | password |
      | /auth/login | bruno@email.com | bruno    |

  Scenario: Verify posts request
    When I request for a post using "/posts/1"
    Then I should see author name as "Karthik KK"

  Scenario Outline: Verify post request
    When I request for a post using "<path>"
    Then I should see author name as "Karthik KK"
    Examples: Post parameters
      | path     |
      | /posts/1 |
      | /posts/2 |

  Scenario Outline: Verify posts request are in the correct format
    When I request for a post using "<path>"
    Then the response from the request for a post should be in the correct format
    Examples: Post parameters
      | path     |
      | /posts/1 |
      | /posts/2 |

  Scenario: Verify posts request contain author name
    When I request for posts using "/posts"
    Then I should see first two author name as "Karthik KK"