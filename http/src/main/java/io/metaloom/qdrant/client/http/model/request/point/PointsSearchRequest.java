package io.metaloom.qdrant.client.http.model.request.point;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.collection.filter.Filter;
import io.metaloom.qdrant.client.http.model.point.NamedVector;
import io.metaloom.qdrant.client.http.model.point.SearchParams;
import io.metaloom.qdrant.client.http.model.request.RestRequest;

public class PointsSearchRequest implements RestRequest {

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

	public Filter getFilter() {
		return filter;
	}

	public SearchParams getParams() {
		return params;
	}

	public int getLimit() {
		return limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public boolean isWithPayload() {
		return withPayload;
	}

	public boolean isWithVector() {
		return withVector;
	}

	public Float getScoreThreshold() {
		return scoreThreshold;
	}

}
