Feature: Validating Place API's
  Scenario: Verify if Place is being added successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with Post Http request
    Then The API call is successful with Status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"


    Examples:
            |name|language|address|
            |AAHouse|English|World Cross Centre|
            |BBHouse|Spanish|World Cross Central|