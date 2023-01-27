package io.metaloom.qdrant.client.http.model.request.point;

import java.util.List;

import io.metaloom.qdrant.client.http.model.point.PointStruct;
import io.metaloom.qdrant.client.http.model.point.PointsBatch;
import io.metaloom.qdrant.client.http.model.request.RestRequest;

public class PointsUpsertRequest implements RestRequest {

	private PointsBatch batch;

	private List<PointStruct> points;

	public PointsBatch getBatch() {
		return batch;
	}

	public List<PointStruct> getPoints() {
		return points;
	}

}
