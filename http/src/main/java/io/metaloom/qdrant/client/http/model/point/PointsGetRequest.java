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

	public PointsGetRequest setIds(List<Long> ids) {
		this.ids = ids;
		return this;
	}

	public boolean isWithPayload() {
		return withPayload;
	}

	public PointsGetRequest setWithPayload(boolean withPayload) {
		this.withPayload = withPayload;
		return this;
	}

	public boolean isWithVector() {
		return withVector;
	}

	public PointsGetRequest setWithVector(boolean withVector) {
		this.withVector = withVector;
		return this;
	}

}
