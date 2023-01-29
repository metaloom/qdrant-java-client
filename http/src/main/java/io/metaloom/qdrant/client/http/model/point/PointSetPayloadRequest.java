package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestRequestModel;
import io.metaloom.qdrant.client.http.model.collection.filter.Filter;

public class PointSetPayloadRequest implements RestRequestModel {

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("points")
	private List<PointId> points;

	@JsonProperty("filter")
	private Filter filter;

	public Payload getPayload() {
		return payload;
	}

	public PointSetPayloadRequest setPayload(Payload payload) {
		this.payload = payload;
		return this;
	}

	public List<PointId> getPoints() {
		return points;
	}

	public PointSetPayloadRequest setPoints(List<PointId> points) {
		this.points = points;
		return this;
	}

	@JsonIgnore
	public PointSetPayloadRequest setPoints(long... values) {
		this.points = PointId.list(values);
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
