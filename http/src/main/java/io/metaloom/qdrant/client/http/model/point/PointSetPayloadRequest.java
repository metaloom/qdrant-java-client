package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import io.metaloom.qdrant.client.http.model.RestRequestModel;
import io.metaloom.qdrant.client.http.model.collection.filter.Filter;

public class PointSetPayloadRequest implements RestRequestModel {

	private Payload payload;

	private List<Long> points;

	private Filter filter;

	public Payload getPayload() {
		return payload;
	}

	public PointSetPayloadRequest setPayload(Payload payload) {
		this.payload = payload;
		return this;
	}

	public List<Long> getPoints() {
		return points;
	}

	public PointSetPayloadRequest setPoints(List<Long> points) {
		this.points = points;
		return this;
	}

	public Filter getFilter() {
		return filter;
	}

	public PointSetPayloadRequest setFilter(Filter filter) {
		this.filter = filter;
		return this;
	}

}
