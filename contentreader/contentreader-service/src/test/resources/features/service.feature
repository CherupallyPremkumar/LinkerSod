Feature: Tests the Chenile Workflow Service using a REST client. Contentreader service exists and is under test.
  It helps to create a contentreader and manages the state of the contentreader as follows:
  OPENED -(assign) -> ASSIGNED -(resolve) -> RESOLVED -(close) -> CLOSED


  Scenario: Close the contentreader with comments
    When I PUT a REST request to URL "/contentreader/0/create" with payload
		"""
		{
        "originalUrl": "https://example.com/original-url",
        "timePeriod": "2024-08-23T10:00:00Z",
        "fullName": "John Doe"
		}
		"""
    Then the REST response contains key "mutatedEntity"
    And store "payload.mutatedEntity.id" from  response to "id"
    And the REST response key "mutatedEntity.id" is "${id}"
    And the REST response key "mutatedEntity.currentState.stateId" is "GENERATED"
    And store "payload.mutatedEntity.shortestUrl" from  response to "shortestUrl"
    And the REST response key "mutatedEntity.fullName" is "John Doe"
    And the REST response key "mutatedEntity.shortestUrl" is "${shortestUrl}"

#  Scenario: Retrieve the order that just got created
#    When I construct a REST request with authorization header in realm "tenant0" for user "t0-normal" and password "t0-normal"
#    And I construct a REST request with header "x-chenile-tenant-id" and value "tenant0"
#    And I construct a REST request with header "x-chenile-location-id" and value "1"
#    And I GET a REST request to URL "/get/${id}"
#
#  Scenario: Assign a closed contentreader to someone. This will err out.
#    When I PUT a REST request to URL "/contentreader/${id}/create" with payload
#		"""
#		{
#
#		}
#		"""
#    Then the REST response does not contain key "mutatedEntity"
#    And the http status code is 422

	  