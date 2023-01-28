package io.metaloom.qdrant.client.http.model.point;

import io.metaloom.qdrant.client.http.model.collection.filter.Filter;

public class PointsFilterDeleteRequest implements PointsDeleteRequest {

	private Filter filter;

	public Filter getFilter() {
		return filter;
	}

	public PointsFilterDeleteRequest setFilter(Filter filter) {
		this.filter = filter;
		return this;
	}
}
