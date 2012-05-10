package com.jtmdb;

import java.util.HashMap;
import java.util.Map;

/**
 * This enumeration provides the responses from the server when using a POST method.
 * @author   Savvas Dalkitsis
 */
public enum ServerResponse {

	/**
	 * @uml.property  name="sUCCESS"
	 * @uml.associationEnd  
	 */
	SUCCESS("Success", 1),
	/**
	 * @uml.property  name="iNVALID_SERVICE"
	 * @uml.associationEnd  
	 */
	INVALID_SERVICE("Invalid service - This service does not exist.", 2),
	/**
	 * @uml.property  name="aUTHENTICATION_FAILED"
	 * @uml.associationEnd  
	 */
	AUTHENTICATION_FAILED(
			"Authentication Failed - You do not have permissions to access the service.",
			3),
	/**
	 * @uml.property  name="iNVALID_FORMAT"
	 * @uml.associationEnd  
	 */
	INVALID_FORMAT(
			"Invalid format - This service doesn't exist in that format.", 4),
	/**
	 * @uml.property  name="iNVALID_PARAMETERS"
	 * @uml.associationEnd  
	 */
	INVALID_PARAMETERS(
			"Invalid parameters - Your request is missing a required parameter.",
			5),
	/**
	 * @uml.property  name="iNVALID_PREREQUISITE_ID"
	 * @uml.associationEnd  
	 */
	INVALID_PREREQUISITE_ID(
			"Invalid pre-requisite id - The pre-requisite id is invalid or not found.",
			6),
	/**
	 * @uml.property  name="iNVALID_API_KEY"
	 * @uml.associationEnd  
	 */
	INVALID_API_KEY("Invalid API key - You must be granted a valid key.", 7),
	/**
	 * @uml.property  name="dUPLICATE_ENTRY"
	 * @uml.associationEnd  
	 */
	DUPLICATE_ENTRY(
			"Duplicate entry - The data you tried to submit already exists.", 8),
	/**
	 * @uml.property  name="sERVER_OFFLINE"
	 * @uml.associationEnd  
	 */
	SERVER_OFFLINE(
			"Service Offline - This service is temporarily offline. Try again later.",
			9),
	/**
	 * @uml.property  name="sUSPENDED_API_KEY"
	 * @uml.associationEnd  
	 */
	SUSPENDED_API_KEY(
			"Suspended API key - Access to your account has been suspended, contact TMDb.",
			10),
	/**
	 * @uml.property  name="iNTERNAL_ERROR"
	 * @uml.associationEnd  
	 */
	INTERNAL_ERROR("Internal error - Something went wrong. Contact TMDb.", 11),
	/**
	 * @uml.property  name="iTEM_RECORD_UPDATED_SUCCESFULLY"
	 * @uml.associationEnd  
	 */
	ITEM_RECORD_UPDATED_SUCCESFULLY("The item/record was updated successfully",
			12),
	/**
	 * @uml.property  name="uNKNOWN_ERROR"
	 * @uml.associationEnd  
	 */
	UNKNOWN_ERROR("There was an unidentified error.", -1);

	/**
	 * This will hold the responses mapped to their code.
	 */
	private static Map<Integer, ServerResponse> pool;

	/**
	 * The message of the response.
	 * @uml.property  name="message"
	 */
	private String message;
	/**
	 * The code of the response.
	 * @uml.property  name="code"
	 */
	private int code;

	/**
	 * The code of the response.
	 * @return  The response of the response.
	 * @uml.property  name="code"
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Sets the code of the response.
	 * @param code  The code of the response.
	 * @uml.property  name="code"
	 */
	public void setCode(int code) {
		this.code = code;
		if (pool == null) {
			pool = new HashMap<Integer, ServerResponse>();
		}
		pool.put(code, this);
	}

	/**
	 * The message of the response.
	 * @return  The message of the response.
	 * @uml.property  name="message"
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set the message of the response.
	 * @param message  The message of the response.
	 * @uml.property  name="message"
	 */
	private void setMessage(String message) {
		this.message = message;
	}

	private ServerResponse(String message, int code) {
		setMessage(message);
		setCode(code);
	}

	/**
	 * Returns the appropriate response for the specified ID.
	 * 
	 * @param id
	 *            The ID of the response.
	 * @return The appropriate response for the specified ID.
	 */
	public static ServerResponse forID(int id) {
		ServerResponse response = pool.get(id);
		if (response == null) {
			response = UNKNOWN_ERROR;
		}
		return response;
	}

}
