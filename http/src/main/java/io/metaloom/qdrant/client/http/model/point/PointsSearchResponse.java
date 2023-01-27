package io.metaloom.qdrant.client.http.model.point;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class PointsSearchResponse extends AbstractResponse {

	private ScoredPoint result;

	public ScoredPoint getResult() {
		return result;
	}
}
