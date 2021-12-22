Feature: Validating Place API's

  @AddPlace
  Scenario Outline: Verify if Place is being added successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "POST" Http request
    Then The API call is successful with Status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"


    Examples:
      | name    | language | address             |
      | AAHouse | English  | World Cross Centre  |
      | BBHouse | Spanish  | World Cross Central |

  @DeletePlace
    Scenario: Verify if Delete Place API is working
      Given DeletePlace payload
      When User calls "deletePlaceAPI" with "POST" Http request
      Then The API call is successful with Status code 200
      And "status" in response body is "OK"