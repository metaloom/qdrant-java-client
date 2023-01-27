package io.metaloom.qdrant.client.http.method;

import com.fasterxml.jackson.databind.JsonNode;

import io.metaloom.qdrant.client.http.HTTPMethods;
import io.metaloom.qdrant.client.http.impl.RequestBuilder;
import io.metaloom.qdrant.client.http.model.request.point.PointCountRequest;
import io.metaloom.qdrant.client.http.model.request.point.PointDeletePayloadRequest;
import io.metaloom.qdrant.client.http.model.request.point.PointOverwritePayloadRequest;
import io.metaloom.qdrant.client.http.model.request.point.PointSetPayloadRequest;
import io.metaloom.qdrant.client.http.model.request.point.PointsDeleteRequest;
import io.metaloom.qdrant.client.http.model.request.point.PointsGetRequest;
import io.metaloom.qdrant.client.http.model.request.point.PointsSearchRequest;
import io.metaloom.qdrant.client.http.model.request.point.PointsUpsertRequest;
import io.metaloom.qdrant.client.http.model.response.point.PointCountResponse;
import io.metaloom.qdrant.client.http.model.response.point.PointGetResponse;
import io.metaloom.qdrant.client.http.model.response.point.PointScrollResponse;
import io.metaloom.qdrant.client.http.model.response.point.PointsGetResponse;
import io.metaloom.qdrant.client.http.model.response.point.PointsSearchResponse;
import io.metaloom.qdrant.client.http.model.response.point.UpdateResultResponse;
import io.metaloom.qdrant.json.Json;

public interface PointMethods extends HTTPMethods {

	default RequestBuilder<PointGetResponse> getPoint(String collectionName, String pointId) {
		RequestBuilder<PointGetResponse> req = getBuilder("collections/" + collectionName + "/points/" + pointId);
		return req;
	}

	default RequestBuilder<PointsGetResponse> getPoints(String collectionName, PointsGetRequest request) {
		RequestBuilder<PointsGetResponse> req = postBuilder("collections/" + collectionName + "/points", request);
		return req;
	}

	default RequestBuilder<UpdateResultResponse> upsertPoints(String collectionName, PointsUpsertRequest request, boolean wait) {
		RequestBuilder<UpdateResultResponse> req  = putBuilder("collections/" + collectionName + "/points", request);
		req.addQueryParameter("wait", String.valueOf(wait));
		return req;
	}

	default RequestBuilder<UpdateResultResponse> deletePoints(String collectionName, PointsDeleteRequest request, boolean wait) {
		RequestBuilder<UpdateResultResponse> req  = postBuilder("collections/" + collectionName + "/points/delete", request);
		req.addQueryParameter("wait", String.valueOf(wait));
		return req;
	}

	default RequestBuilder<UpdateResultResponse> setPointPayload(String collectionName, PointSetPayloadRequest request, boolean wait) {
		RequestBuilder<UpdateResultResponse> req = postBuilder("collections/" + collectionName + "/points/payload", request);
		req.addQueryParameter("wait", String.valueOf(wait));
		return req;
	}

	default RequestBuilder<UpdateResultResponse> overwritePayload(String collectionName, PointOverwritePayloadRequest request, boolean wait) {
		RequestBuilder<UpdateResultResponse> req =putBuilder("collections/" + collectionName + "/points/payload", request);
		req.addQueryParameter("wait", String.valueOf(wait));
		return req;
	}

	default RequestBuilder<UpdateResultResponse> deletePayload(String collectionName, PointDeletePayloadRequest request, boolean wait) {
		RequestBuilder<UpdateResultResponse> req = postBuilder("collections/" + collectionName + "/points/payload/delete", request);
		req.addQueryParameter("wait", String.valueOf(wait));
		return req;
	}

	default RequestBuilder<UpdateResultResponse> clearPayload(String collectionName, JsonNode json, boolean wait) {
		RequestBuilder<UpdateResultResponse> req = postBuilder("collections/" + collectionName + "/points/payload/clear", json);
		req.addQueryParameter("wait", String.valueOf(wait));
		return req;
	}

	default RequestBuilder<PointScrollResponse> scrollPoints(String collectionName, JsonNode json) {
		return postBuilder("collections/" + collectionName + "/points/scroll", json);
	}

	default RequestBuilder<PointsSearchResponse> searchPoints(String collectionName, PointsSearchRequest request) {
		RequestBuilder<PointsSearchResponse> req = postBuilder("collections/" + collectionName + "/points/search", request);
		return req;
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
