package io.metaloom.qdrant.client.http.model.point;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestRequestModel;
import io.metaloom.qdrant.client.http.model.collection.filter.Filter;

public class PointDeletePayloadRequest implements RestRequestModel {

	/**
	 * List of payload keys to remove from payload
	 */
	@JsonProperty("keys")
	private List<String> keys;

	/**
	 * Deletes values from each point in this list
	 */
	@JsonProperty("points")
	private List<PointId> points;

	/**
	 * Deletes values from points that satisfy this filter condition
	 */
	@JsonProperty("filter")
	private Filter filter;

	public List<String> getKeys() {
		return keys;
	}

	public PointDeletePayloadRequest setKeys(List<String> keys) {
		this.keys = keys;
		return this;
	}

	@JsonIgnore
	public PointDeletePayloadRequest setKeys(String... keys) {
		this.keys = Arrays.asList(keys);
		return this;
	}

	public List<PointId> getPoints() {
		return points;
	}

	public PointDeletePayloadRequest setPoints(List<PointId> points) {
		this.points = points;
		return this;
	}

	@JsonIgnore
	public PointDeletePayloadRequest setPoints(long... ids) {
		this.points = PointId.list(ids);
		return this;
	}

	public Filter getFilter() {
		return filter;
	}

	public PointDeletePayloadRequest setFilter(Filter filter) {
		this.filter = filter;
		return this;
	}
}
