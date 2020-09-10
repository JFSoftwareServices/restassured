Feature: Request Post

  Background:

  Background:
    Given I authenticate using:
      | <uri>       | email           | password |
      | /auth/login | bruno@email.com | bruno    |

  Scenario: Verify authors post request
    When I request for a post using "/posts/1"
    Then I should see author name as "Karthik KK"

  Scenario Outline: Verify authors post request contain author name
    When I request for a post using "<uri>"
    Then I should see author name as "Karthik KK"
    Examples:
      | uri      |
      | /posts/1 |
      | /posts/2 |

  Scenario Outline: Verify authors post request are in the correct format
    When I request for a post using "<uri>"
    Then the response from the retrieve "post" should be in the correct format
    Examples:
      | uri      |
      | /posts/1 |
      | /posts/2 |

  Scenario: Verify authors posts request contain author name
    When I request for posts using "/posts"
    Then I should see first two author name as "Karthik KK"


  Scenario Outline: Verify location contain street name in address
    When I request for location using "<uri>"
    Then I should see the street name as "1st street"
    Examples:
      | uri            |
      | /location?id=1 |