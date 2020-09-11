Feature: Location

  Background:
    Given I authenticate using:
      | path        | email           | password |
      | /auth/login | bruno@email.com | bruno    |

  Scenario Outline: Verify location contain street name in address
    When I request for location using "<path>", "<locationIdKey>", <locationIdValue>
    Then I should see the street name as "1st street"
    Examples:
      | path      | locationIdKey | locationIdValue |
      | /location | id            | 1               |