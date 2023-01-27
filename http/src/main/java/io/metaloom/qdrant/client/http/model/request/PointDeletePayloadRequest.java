package io.metaloom.qdrant.client.http.model.request;

import java.util.List;

import io.metaloom.qdrant.client.http.model.collection.ExtendedPointId;
import io.metaloom.qdrant.client.http.model.collection.filter.Filter;

public class PointDeletePayloadRequest {

	/**
	 * List of payload keys to remove from payload
	 */
	List<String> keys;

	/**
	 * Deletes values from each point in this list
	 */
	List<ExtendedPointId> points;

	/**
	 * Deletes values from points that satisfy this filter condition
	 */
	Filter filter;
}
