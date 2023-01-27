package io.metaloom.qdrant.client.http;

import com.fasterxml.jackson.databind.JsonNode;

import io.metaloom.qdrant.client.http.impl.RequestBuilder;
import io.metaloom.qdrant.client.http.model.RestRequest;
import io.metaloom.qdrant.client.http.model.RestResponse;
import io.metaloom.qdrant.json.Json;

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
	<T extends RestResponse> RequestBuilder<T> actionBuilder(String method, String path, JsonNode... json);

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
	<T extends RestResponse> RequestBuilder<T> actionBuilder(String method, String path, String bulkData);

	default <T extends RestResponse> RequestBuilder<T> putBuilder(String path, JsonNode json) {
		return actionBuilder(PUT, path, json);
	}

	default <T extends RestResponse> RequestBuilder<T> deleteBuilder(String path) {
		return actionBuilder(DELETE, path);
	}

	default <T extends RestResponse> RequestBuilder<T> deleteBuilder(String path, JsonNode json) {
		return actionBuilder(DELETE, path, json);
	}

	default <T extends RestResponse> RequestBuilder<T> getBuilder(String path) {
		return actionBuilder(GET, path);
	}

	default <T extends RestResponse> RequestBuilder<T> postBuilder(String path, JsonNode... json) {
		return actionBuilder(POST, path, json);
	}

	default <T extends RestResponse> RequestBuilder<T> postBuilder(String path, RestRequest request) {
		return actionBuilder(POST, path, Json.parse(request));
	}

	default <T extends RestResponse> RequestBuilder<T> putBuilder(String path, RestRequest request) {
		return actionBuilder(PUT, path, Json.parse(request));
	}

	default <T extends RestResponse> RequestBuilder<T> postBuilder(String path, String bulkData) {
		return actionBuilder(POST, path, bulkData);
	}

}
