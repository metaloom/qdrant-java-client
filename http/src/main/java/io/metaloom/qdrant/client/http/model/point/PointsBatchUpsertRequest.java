package io.metaloom.qdrant.client.http.model.point;

public class PointsBatchUpsertRequest implements PointsUpsertRequest {

	private PointsBatch batch;

	public PointsBatch getBatch() {
		return batch;
	}

	public PointsBatchUpsertRequest setBatch(PointsBatch batch) {
		this.batch = batch;
		return this;
	}

}
