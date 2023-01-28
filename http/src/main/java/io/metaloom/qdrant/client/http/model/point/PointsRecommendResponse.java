package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class PointsRecommendResponse extends AbstractResponse {

	private List<ScoredPoint> result;

	public List<ScoredPoint> getResult() {
		return result;
	}
}
