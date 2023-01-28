package io.metaloom.qdrant.client.http.model.point;

import static io.metaloom.qdrant.client.util.QDrantClientUtil.toList;

import java.util.List;

public class PointsListDeleteRequest implements PointsDeleteRequest {

	private List<Long> points;

	public List<Long> getPoints() {
		return points;
	}

	public PointsListDeleteRequest setPoints(List<Long> points) {
		this.points = points;
		return this;
	}

	public PointsListDeleteRequest setPoints(long... ids) {
		this.points = toList(ids);
		return this;
	}

}
