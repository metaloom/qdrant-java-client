package io.metaloom.qdrant.client.http.method;

import com.fasterxml.jackson.databind.JsonNode;

import io.metaloom.qdrant.client.http.HTTPMethods;
import io.metaloom.qdrant.client.http.impl.RequestBuilder;
import io.metaloom.qdrant.client.http.model.request.PointCountRequest;
import io.metaloom.qdrant.client.http.model.response.PointCountResponse;
import io.metaloom.qdrant.json.Json;

public interface PointMethods extends HTTPMethods {

	default RequestBuilder getPoint(String collectionName, String pointId) {
		return getBuilder("collections/" + collectionName + "/points/" + pointId);
	}

	default RequestBuilder getPoints(String collectionName, JsonNode json) {
		return postBuilder("collections/" + collectionName + "/points", json);
	}

	default RequestBuilder upsertPoints(String collectionName, JsonNode json) {
		return putBuilder("collections/" + collectionName + "/points", json);
	}

	default RequestBuilder deletePoints(String collectionName, JsonNode json) {
		return deleteBuilder("collections/" + collectionName + "/points/delete", json);
	}

	default RequestBuilder setPayload(String collectionName, JsonNode json) {
		return postBuilder("collections/" + collectionName + "/points/payload", json);
	}

	default RequestBuilder overwritePayload(String collectionName, JsonNode json) {
		return putBuilder("collections/" + collectionName + "/points/payload", json);
	}

	default RequestBuilder deletePayload(String collectionName, JsonNode json) {
		return postBuilder("collections/" + collectionName + "/points/payload/delete", json);
	}

	default RequestBuilder clearPayload(String collectionName, JsonNode json) {
		return postBuilder("collections/" + collectionName + "/points/payload/clear", json);
	}

	default RequestBuilder scrollPoints(String collectionName, JsonNode json) {
		return postBuilder("collections/" + collectionName + "/points/scroll", json);
	}

	default RequestBuilder searchPoints(String collectionName, JsonNode json) {
		return postBuilder("collections/" + collectionName + "/points/search", json);
	}

	default RequestBuilder searchBatchPoints(String collectionName, JsonNode json) {
		return postBuilder("collections/" + collectionName + "/points/search/batch", json);
	}

	default RequestBuilder recommendPoints(String collectionName, JsonNode json) {
		return postBuilder("collections/" + collectionName + "/points/recommend", json);
	}

	default RequestBuilder recommendBatchPoints(String collectionName, JsonNode json) {
		return postBuilder("collections/" + collectionName + "/points/recommend/batch", json);
	}

	default RequestBuilder<PointCountResponse> countPoints(String collectionName, PointCountRequest request) {
		return postBuilder("collections/" + collectionName + "/points/count", Json.parse(request));
	}

}
