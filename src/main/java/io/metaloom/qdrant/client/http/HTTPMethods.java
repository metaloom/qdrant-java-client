package io.metaloom.qdrant.client.http;

import org.json.JSONObject;

import io.metaloom.qdrant.client.http.impl.RequestBuilder;

public interface HTTPMethods {

	static final String PUT = "PUT";
	static final String GET = "GET";
	static final String DELETE = "DELETE";
	static final String POST = "POST";

	/**
	 * Create the given request to the server and return the builder which can be used to invoke the request.
	 * 
	 * @param method
	 *            Http method
	 * @param path
	 *            Request path
	 * @param json
	 *            Body data or null if no body should be send
	 * @return Created builder
	 */
	RequestBuilder actionBuilder(String method, String path, JSONObject... json);

	/**
	 * Create the given request to the server and return the builder which can be used to inoke the request.
	 * 
	 * @param method
	 *            Http method
	 * @param path
	 *            Request path
	 * @param bulkData
	 *            Body data or null if no body should be send
	 * @return Created builder
	 */
	RequestBuilder actionBuilder(String method, String path, String bulkData);

	default RequestBuilder putBuilder(String path, JSONObject json) {
		return actionBuilder(PUT, path, json);
	}

	default RequestBuilder deleteBuilder(String path) {
		return actionBuilder(DELETE, path);
	}

	default RequestBuilder deleteBuilder(String path, JSONObject json) {
		return actionBuilder(DELETE, path, json);
	}

	default RequestBuilder getBuilder(String path) {
		return actionBuilder(GET, path);
	}

	default RequestBuilder postBuilder(String path, JSONObject... json) {
		return actionBuilder(POST, path, json);
	}

	default RequestBuilder postBuilder(String path, String bulkData) {
		return actionBuilder(POST, path, bulkData);
	}

}
