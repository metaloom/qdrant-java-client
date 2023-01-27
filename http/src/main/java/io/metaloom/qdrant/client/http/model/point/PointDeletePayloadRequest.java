package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import io.metaloom.qdrant.client.http.model.RestRequest;
import io.metaloom.qdrant.client.http.model.collection.filter.Filter;

public class PointDeletePayloadRequest implements RestRequest {

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

	public List<Long> getPoints() {
		return points;
	}

	public Filter getFilter() {
		return filter;
	}
}
