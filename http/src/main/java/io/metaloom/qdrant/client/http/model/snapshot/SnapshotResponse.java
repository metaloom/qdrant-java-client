package io.metaloom.qdrant.client.http.model.snapshot;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class SnapshotResponse extends AbstractResponse {

	private SnapshotDescription result;

	public SnapshotDescription getResult() {
		return result;
	}
}
