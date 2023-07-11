package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class ScrollResult implements RestModel {

	private List<Record> points;

	@JsonProperty("next_page_offset")
	private PointId nextPageOffset;

	public List<Record> getPoints() {
		return points;
	}

	public ScrollResult setPoints(List<Record> points) {
		this.points = points;
		return this;
	}

	public PointId getNextPageOffset() {
		return nextPageOffset;
	}

	public ScrollResult setNextPageOffset(PointId nextPageOffset) {
		this.nextPageOffset = nextPageOffset;
		return this;
	}
}
