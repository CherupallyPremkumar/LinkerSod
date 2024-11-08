Feature: Tests the Chenile Workflow Service using a REST client. Chatapp service exists and is under test.
It helps to create a chatapp and manages the state of the chatapp as follows:
OPENED -(assign) -> ASSIGNED -(resolve) -> RESOLVED -(close) -> CLOSED
 
  Scenario: Test create chatapp
	  When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
	  And I construct a REST request with header "x-chenile-tenant-id" and value "tenant0"
    When I PUT a REST request to URL "/chatapp/0/createUser" with payload
    """
{
  "email": "example@example.com",
  "username": "exampleUser",
  "password": "password123",
  "fullName": "Example Full Name",
  "avatarUrl": "https://example.com/avatar.jpg"
}

	"""
    Then the REST response contains key "mutatedEntity"
    And store "$.payload.mutatedEntity.id" from  response to "id1"
	  And store "$.payload.mutatedEntity.users.id" from  response to "userid1"
    And the REST response key "mutatedEntity.currentState.stateId" is "OPENED"

	Scenario: Test create chatapp
		When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
		And I construct a REST request with header "x-chenile-tenant-id" and value "tenant0"
		When I PUT a REST request to URL "/chatapp/0/createUser" with payload
    """
{
  "email": "examle@example.com",
  "username": "exampleUser",
  "password": "password123",
  "fullName": "Example Full Name",
  "avatarUrl": "https://example.com/avatar.jpg"
}

	"""
		Then the REST response contains key "mutatedEntity"
		And store "$.payload.mutatedEntity.id" from  response to "id2"
		And store "$.payload.mutatedEntity.users.id" from  response to "userid2"
		And the REST response key "mutatedEntity.currentState.stateId" is "OPENED"

	Scenario: Assign a closed chatapp to someone. This will err out.
		When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
		And I construct a REST request with header "x-chenile-tenant-id" and value "tenant0"
		When I PUT a REST request to URL "/chatapp/0/createUser" with payload
		"""
		{
  "email": "example@example.com",
  "username": "exampleUser",
  "password": "password123",
  "fullName": "Example Full Name",
  "avatarUrl": "https://example.com/avatar.jpg"
}
		"""
		Then the REST response does not contain key "mutatedEntity"
		And the http status code is 500
	  
	Scenario: Retrieve the chatapp that just got created
		When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
		And I construct a REST request with header "x-chenile-tenant-id" and value "tenant0"
	  When I GET a REST request to URL "/chatapp/${id1}"
	  Then the REST response contains key "mutatedEntity"
	  And the REST response key "mutatedEntity.id" is "${id1}"
	  And the REST response key "mutatedEntity.currentState.stateId" is "OPENED"
	  
	Scenario: Assign the chatapp to an assignee with comments
		When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
		And I construct a REST request with header "x-chenile-tenant-id" and value "tenant0"
		When I PUT a REST request to URL "/chatapp/${id1}/addFriends" with payload
		"""
		{
  "userId": "${userid1}",
  "friendId": "${userid2}"
}
		"""
	  Then the REST response contains key "mutatedEntity"
	  And the REST response key "mutatedEntity.id" is "${id1}"
	  And the REST response key "mutatedEntity.currentState.stateId" is "OPENED"
	  
	 Scenario: Resolve the chatapp with comments
		When I PUT a REST request to URL "/chatapp/${id}/resolve" with payload
		"""
		{
			"comment": "CANNOT-DUPLICATE"
		}
		"""
	  Then the REST response contains key "mutatedEntity"
	  And the REST response key "mutatedEntity.id" is "${id}"
	  And the REST response key "mutatedEntity.currentState.stateId" is "RESOLVED"
	  And the REST response key "mutatedEntity.resolveComment" is "CANNOT-DUPLICATE"
	  
	 Scenario: Close the chatapp with comments
		When I PUT a REST request to URL "/chatapp/${id}/close" with payload
		"""
		{
			"comment": "OK"
		}
		"""
	  Then the REST response contains key "mutatedEntity"
	  And the REST response key "mutatedEntity.id" is "${id}"
	  And the REST response key "mutatedEntity.currentState.stateId" is "CLOSED"
	  And the REST response key "mutatedEntity.closeComment" is "OK" 
	  
	 Scenario: Assign a closed chatapp to someone. This will err out.
		When I PUT a REST request to URL "/chatapp/${id}/assign" with payload
		"""
		{
			"assignee": "MY-ASSIGNEE",
			"comment": "MY-ASSIGNEE-CAN-FIX-THIS"
		}
		"""
		Then the REST response does not contain key "mutatedEntity"
		And the http status code is 422

	  