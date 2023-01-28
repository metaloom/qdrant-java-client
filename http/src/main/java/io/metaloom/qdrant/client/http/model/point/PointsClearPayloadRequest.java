package io.metaloom.qdrant.client.http.model.point;

import static io.metaloom.qdrant.client.util.QDrantClientUtil.toList;

import java.util.List;

import io.metaloom.qdrant.client.http.model.RestRequestModel;
import io.metaloom.qdrant.client.http.model.collection.filter.Filter;

public class PointsClearPayloadRequest implements RestRequestModel {

	private List<Long> points;

	private Filter filter;

	public List<Long> getPoints() {
		return points;
	}

	public PointsClearPayloadRequest setPoints(List<Long> points) {
		this.points = points;
		return this;
	}

	public PointsClearPayloadRequest setPoints(long... points) {
		this.points = toList(points);
		return this;
	}

	public Filter getFilter() {
		return filter;
	}

	public PointsClearPayloadRequest setFilter(Filter filter) {
		this.filter = filter;
		return this;
	}

}
