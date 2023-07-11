package io.metaloom.qdrant.client.http.model.point;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestRequestModel;
import io.metaloom.qdrant.client.http.model.collection.filter.Filter;

public class PointsScrollRequest implements RestRequestModel {

	private PointId offset;

	private Integer limit;

	private Filter filter;

	@JsonProperty("with_payload")
	private boolean withPayload;

	@JsonProperty("with_vector")
	private boolean withVector;

	public PointId getOffset() {
		return offset;
	}

	public PointsScrollRequest setOffset(PointId offset) {
		this.offset = offset;
		return this;
	}

	public Integer getLimit() {
		return limit;
	}

	public PointsScrollRequest setLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

	public Filter getFilter() {
		return filter;
	}

	public PointsScrollRequest setFilter(Filter filter) {
		this.filter = filter;
		return this;
	}

	public boolean getWithPayload() {
		return withPayload;
	}

	public PointsScrollRequest setWithPayload(boolean withPayload) {
		this.withPayload = withPayload;
		return this;
	}

	public boolean getWithVector() {
		return withVector;
	}

	public PointsScrollRequest setWithVector(boolean withVector) {
		this.withVector = withVector;
		return this;
	}
}
