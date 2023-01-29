package io.metaloom.qdrant.client.http.model.point;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PointsListUpsertRequest implements PointsUpsertRequest {

	@JsonProperty("points")
	private List<PointStruct> points;

	public List<PointStruct> getPoints() {
		return points;
	}

	public PointsListUpsertRequest setPoints(List<PointStruct> points) {
		this.points = points;
		return this;
	}

	@JsonIgnore
	public PointsListUpsertRequest setPoints(PointStruct... points) {
		this.points = Arrays.asList(points);
		return this;
	}

}
