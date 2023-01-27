package io.metaloom.qdrant.client.http.model.response;

import io.metaloom.qdrant.client.http.model.point.CountResult;

public class PointCountResponse implements RestResponse {

	float time;

	String status;

	CountResult result;
}
