package io.metaloom.qdrant.client.http.model.point;

import io.metaloom.qdrant.client.http.model.RestRequest;
import io.metaloom.qdrant.client.http.model.collection.filter.Filter;

public class PointCountRequest implements RestRequest {

	private Filter filter;

	private Boolean exact;

	public Filter getFilter() {
		return filter;
	}

	public Boolean getExact() {
		return exact;
	}
}
