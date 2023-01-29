package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

public class PointsListDeleteRequest implements PointsDeleteRequest {

	private List<PointId> points;

	public List<PointId> getPoints() {
		return points;
	}

	public PointsListDeleteRequest setPoints(List<PointId> points) {
		this.points = points;
		return this;
	}

	public PointsListDeleteRequest setPoints(long... ids) {
		this.points = PointId.list(ids);
		return this;
	}

}
