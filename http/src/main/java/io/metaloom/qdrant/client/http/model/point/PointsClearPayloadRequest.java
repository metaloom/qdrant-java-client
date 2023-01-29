package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.metaloom.qdrant.client.http.model.RestRequestModel;
import io.metaloom.qdrant.client.http.model.collection.filter.Filter;

public class PointsClearPayloadRequest implements RestRequestModel {

	private List<PointId> points;

	private Filter filter;

	public List<PointId> getPoints() {
		return points;
	}

	public PointsClearPayloadRequest setPoints(List<PointId> points) {
		this.points = points;
		return this;
	}

	@JsonIgnore
	public PointsClearPayloadRequest setPoints(long... points) {
		this.points = PointId.list(points);
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
