Feature: Request for author posts

  Background:
    Given I perform authentication operation for "/auth/login" with body
      | email           | password |
      | bruno@email.com | bruno    |

  Scenario: Verify authors post request
    When I request for "/posts/1"
    Then I should see author name as "Karthik KK"

  Scenario Outline: Verify authors post request contain author name
    When I request for "<uri>"
    Then I should see author name as "Karthik KK"
    Examples:
      | uri      |
      | /posts/1 |
      | /posts/2 |

  Scenario Outline: Verify authors post request are in the correct format
    When I request for "<uri>"
    Then the response from the retrieve "post" should be in the correct format
    Examples:
      | uri      |
      | /posts/1 |
      | /posts/2 |

  Scenario: Verify authors posts request contain author name
    When I request for "/posts"
    Then I should see first two author name as "Karthik KK"

  Scenario Outline: Verify location contain street name in address
    When I request for "<uri>"
    Then I should see the street name as "1st street"
    Examples:
      | uri            |
      | /location?id=1 |