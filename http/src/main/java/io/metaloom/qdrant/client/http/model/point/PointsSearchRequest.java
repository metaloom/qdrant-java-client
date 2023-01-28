package io.metaloom.qdrant.client.http.model.point;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestRequestModel;
import io.metaloom.qdrant.client.http.model.collection.filter.Filter;

public class PointsSearchRequest implements RestRequestModel {

	private NamedVector vector;

	private Filter filter;

	private SearchParams params;

	private int limit;

	private Integer offset;

	@JsonProperty("with_payload")
	private boolean withPayload;

	@JsonProperty("with_vector")
	private boolean withVector;

	@JsonProperty("score_threshold")
	private Float scoreThreshold;

	public NamedVector getVector() {
		return vector;
	}

	public PointsSearchRequest setVector(NamedVector vector) {
		this.vector = vector;
		return this;
	}

	public Filter getFilter() {
		return filter;
	}

	public PointsSearchRequest setFilter(Filter filter) {
		this.filter = filter;
		return this;
	}

	public SearchParams getParams() {
		return params;
	}

	public PointsSearchRequest setParams(SearchParams params) {
		this.params = params;
		return this;
	}

	public int getLimit() {
		return limit;
	}

	public PointsSearchRequest setLimit(int limit) {
		this.limit = limit;
		return this;
	}

	public Integer getOffset() {
		return offset;
	}

	public PointsSearchRequest setOffset(Integer offset) {
		this.offset = offset;
		return this;
	}

	public boolean isWithPayload() {
		return withPayload;
	}

	public PointsSearchRequest setWithPayload(boolean withPayload) {
		this.withPayload = withPayload;
		return this;
	}

	public boolean isWithVector() {
		return withVector;
	}

	public PointsSearchRequest setWithVector(boolean withVector) {
		this.withVector = withVector;
		return this;
	}

	public Float getScoreThreshold() {
		return scoreThreshold;
	}

	public PointsSearchRequest setScoreThreshold(Float scoreThreshold) {
		this.scoreThreshold = scoreThreshold;
		return this;
	}

}
