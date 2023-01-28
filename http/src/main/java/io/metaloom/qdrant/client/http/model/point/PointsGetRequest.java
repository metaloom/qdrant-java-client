package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestRequestModel;

public class PointsGetRequest implements RestRequestModel {

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
