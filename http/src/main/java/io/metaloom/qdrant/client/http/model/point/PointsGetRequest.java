package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestRequest;

public class PointsGetRequest implements RestRequest {

	private List<Long> ids;

	@JsonProperty("with_payload")
	private boolean withPayload;

	@JsonProperty("with_vector")
	private boolean withVector;

	public List<Long> getIds() {
		return ids;
	}

	public boolean isWithPayload() {
		return withPayload;
	}

	public boolean isWithVector() {
		return withVector;
	}

}
