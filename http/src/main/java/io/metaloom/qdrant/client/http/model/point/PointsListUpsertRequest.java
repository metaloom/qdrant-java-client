package io.metaloom.qdrant.client.http.model.point;

import java.util.Arrays;
import java.util.List;

public class PointsListUpsertRequest implements PointsUpsertRequest {

	private List<PointStruct> points;

	public List<PointStruct> getPoints() {
		return points;
	}

	public PointsListUpsertRequest setPoints(List<PointStruct> points) {
		this.points = points;
		return this;
	}

	public PointsListUpsertRequest setPoints(PointStruct... points) {
		this.points = Arrays.asList(points);
		return this;
	}

}
