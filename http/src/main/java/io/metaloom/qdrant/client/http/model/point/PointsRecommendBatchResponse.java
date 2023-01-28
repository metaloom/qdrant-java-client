package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class PointsRecommendBatchResponse extends AbstractResponse {

	private List<List<ScoredPoint>> result;

	public List<List<ScoredPoint>> getResult() {
		return result;
	}

}
