package io.metaloom.qdrant.client.http.model.response.point;

import io.metaloom.qdrant.client.http.model.point.ScoredPoint;
import io.metaloom.qdrant.client.http.model.response.AbstractResponse;

public class PointsSearchResponse extends AbstractResponse {

	private ScoredPoint result;

	public ScoredPoint getResult() {
		return result;
	}
}
