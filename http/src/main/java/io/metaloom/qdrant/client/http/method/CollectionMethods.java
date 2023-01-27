package io.metaloom.qdrant.client.http.method;

import com.fasterxml.jackson.databind.JsonNode;

import io.metaloom.qdrant.client.http.HTTPMethods;
import io.metaloom.qdrant.client.http.impl.RequestBuilder;

/**
 * API methods which are used to interact with collections.
 */
public interface CollectionMethods extends HTTPMethods {

	default RequestBuilder listCollections() {
		return getBuilder("collections");
	}

	default RequestBuilder createCollection(String name, JsonNode json) {
		return putBuilder("collections/" + name, json);
	}

	default RequestBuilder deleteCollection(String name) {
		return deleteBuilder("collections/" + name);
	}
}
