package io.metaloom.qdrant.client.http.model.point;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestRequestModel;
import io.metaloom.qdrant.client.http.model.collection.filter.Filter;

public class PointsSearchGroupRequest implements RestRequestModel {

	private NamedVector vector;

	private Filter filter;

	private SearchParams params;

	@JsonProperty("with_payload")
	private Boolean withPayload;

	@JsonProperty("with_vector")
	private Boolean withVector;

	@JsonProperty("score_threshold")
	private Float scoreThreshold;

	@JsonProperty("group_by")
	private String groupBy;

	@JsonProperty("group_size")
	private Integer groupSize;

	private int limit;

	public NamedVector getVector() {
		return vector;
	}

	public PointsSearchGroupRequest setVector(NamedVector vector) {
		this.vector = vector;
		return this;
	}

	public Filter getFilter() {
		return filter;
	}

	public PointsSearchGroupRequest setFilter(Filter filter) {
		this.filter = filter;
		return this;
	}

	public SearchParams getParams() {
		return params;
	}

	public PointsSearchGroupRequest setParams(SearchParams params) {
		this.params = params;
		return this;
	}

	public Boolean getWithPayload() {
		return withPayload;
	}

	public PointsSearchGroupRequest setWithPayload(Boolean withPayload) {
		this.withPayload = withPayload;
		return this;
	}

	public Boolean getWithVector() {
		return withVector;
	}

	public PointsSearchGroupRequest setWithVector(Boolean withVector) {
		this.withVector = withVector;
		return this;
	}

	public Float getScoreThreshold() {
		return scoreThreshold;
	}

	public PointsSearchGroupRequest setScoreThreshold(Float scoreThreshold) {
		this.scoreThreshold = scoreThreshold;
		return this;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public PointsSearchGroupRequest setGroupBy(String groupBy) {
		this.groupBy = groupBy;
		return this;
	}

	public Integer getGroupSize() {
		return groupSize;
	}

	public PointsSearchGroupRequest setGroupSize(Integer groupSize) {
		this.groupSize = groupSize;
		return this;
	}

	public int getLimit() {
		return limit;
	}

	public PointsSearchGroupRequest setLimit(int limit) {
		this.limit = limit;
		return this;
	}

}
