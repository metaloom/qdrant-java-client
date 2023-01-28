package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import io.metaloom.qdrant.client.http.model.RestRequestModel;
import io.metaloom.qdrant.client.http.model.collection.filter.Filter;

public class PointDeletePayloadRequest implements RestRequestModel {

	/**
	 * List of payload keys to remove from payload
	 */
	private List<String> keys;

	/**
	 * Deletes values from each point in this list
	 */
	private List<Long> points;

	/**
	 * Deletes values from points that satisfy this filter condition
	 */
	private Filter filter;

	public List<String> getKeys() {
		return keys;
	}

	public PointDeletePayloadRequest setKeys(List<String> keys) {
		this.keys = keys;
		return this;
	}

	public List<Long> getPoints() {
		return points;
	}

	public PointDeletePayloadRequest setPoints(List<Long> points) {
		this.points = points;
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
