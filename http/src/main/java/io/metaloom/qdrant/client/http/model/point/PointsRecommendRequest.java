package io.metaloom.qdrant.client.http.model.point;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestRequestModel;
import io.metaloom.qdrant.client.http.model.collection.filter.Filter;

public class PointsRecommendRequest implements RestRequestModel {

	@JsonProperty("positive")
	private List<PointId> positive;

	@JsonProperty("negative")
	private List<PointId> negative;

	@JsonProperty("filter")
	private Filter filter;

	@JsonProperty("params")
	private SearchParams params;

	@JsonProperty("limit")
	private int limit;

	@JsonProperty("offset")
	private Integer offset;

	@JsonProperty("with_payload")
	private Boolean withPayload;

	@JsonProperty("with_vector")
	private Boolean withVector;

	@JsonProperty("score_threshold")
	private Float scoreThreshold;

	@JsonProperty("using")
	private String using;

	@JsonProperty("lookup_from")
	private LookupLocation lookupFrom;

	public List<PointId> getPositive() {
		return positive;
	}

	public void setPositive(List<PointId> positive) {
		this.positive = positive;
	}

	public List<PointId> getNegative() {
		return negative;
	}

	public void setNegative(List<PointId> negative) {
		this.negative = negative;
	}

	public Filter getFilter() {
		return filter;
	}

	public PointsRecommendRequest setFilter(Filter filter) {
		this.filter = filter;
		return this;
	}

	public SearchParams getParams() {
		return params;
	}

	public PointsRecommendRequest setParams(SearchParams params) {
		this.params = params;
		return this;
	}

	public int getLimit() {
		return limit;
	}

	public PointsRecommendRequest setLimit(int limit) {
		this.limit = limit;
		return this;
	}

	public Integer getOffset() {
		return offset;
	}

	public PointsRecommendRequest setOffset(Integer offset) {
		this.offset = offset;
		return this;
	}

	public Boolean getWithPayload() {
		return withPayload;
	}

	public PointsRecommendRequest setWithPayload(Boolean withPayload) {
		this.withPayload = withPayload;
		return this;
	}

	public Boolean getWithVector() {
		return withVector;
	}

	public PointsRecommendRequest setWithVector(Boolean withVector) {
		this.withVector = withVector;
		return this;
	}

	public Float getScoreThreshold() {
		return scoreThreshold;
	}

	public PointsRecommendRequest setScoreThreshold(Float scoreThreshold) {
		this.scoreThreshold = scoreThreshold;
		return this;
	}

	public String getUsing() {
		return using;
	}

	public PointsRecommendRequest setUsing(String using) {
		this.using = using;
		return this;
	}

	public LookupLocation getLookupFrom() {
		return lookupFrom;
	}

	public PointsRecommendRequest setLookupFrom(LookupLocation lookupFrom) {
		this.lookupFrom = lookupFrom;
		return this;
	}

	@JsonIgnore
	public PointsRecommendRequest setPositive(long... ids) {
		this.positive = PointId.list(ids);
		return this;
	}

	@JsonIgnore
	public PointsRecommendRequest setPositive(String... ids) {
		this.positive = PointId.list(ids);
		return this;
	}

	@JsonIgnore
	public PointsRecommendRequest setPositive(UUID... ids) {
		this.positive = PointId.list(ids);
		return this;
	}
}
