package io.metaloom.qdrant.client.http.model.request.point;

import java.util.List;

import io.metaloom.qdrant.client.http.model.collection.filter.Filter;
import io.metaloom.qdrant.client.http.model.point.Payload;
import io.metaloom.qdrant.client.http.model.request.RestRequest;

public class PointSetPayloadRequest implements RestRequest {

	private Payload payload;

	private List<Long> points;

	private Filter filter;

	public Payload getPayload() {
		return payload;
	}

	public List<Long> getPoints() {
		return points;
	}

	public Filter getFilter() {
		return filter;
	}

}
