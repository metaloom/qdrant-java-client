package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class PointsSearchBatchResponse extends AbstractResponse {

	private List<List<ScoredPoint>> result;

	public List<List<ScoredPoint>> getResult() {
		return result;
	}

	public PointsSearchBatchResponse setResult(List<List<ScoredPoint>> result) {
		this.result = result;
		return this;
	}
}
