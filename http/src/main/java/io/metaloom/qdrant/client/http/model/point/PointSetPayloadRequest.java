package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import io.metaloom.qdrant.client.http.model.RestRequest;
import io.metaloom.qdrant.client.http.model.collection.filter.Filter;

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
