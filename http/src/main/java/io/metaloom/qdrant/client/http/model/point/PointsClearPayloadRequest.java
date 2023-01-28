package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import io.metaloom.qdrant.client.http.model.RestRequestModel;
import io.metaloom.qdrant.client.http.model.collection.filter.Filter;

public class PointsClearPayloadRequest implements RestRequestModel {

	private List<Long> points;

	private Filter filter;

	public List<Long> getPoints() {
		return points;
	}

	public void setPoints(List<Long> points) {
		this.points = points;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

}
