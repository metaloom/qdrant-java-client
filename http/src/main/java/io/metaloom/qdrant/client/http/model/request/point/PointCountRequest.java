package io.metaloom.qdrant.client.http.model.request.point;

import io.metaloom.qdrant.client.http.model.collection.filter.Filter;
import io.metaloom.qdrant.client.http.model.request.RestRequest;

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
