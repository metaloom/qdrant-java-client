package io.metaloom.qdrant.client.http.model.snapshot;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class SnapshotCreateResponse extends AbstractResponse {

	private SnapshotDescription result;

	public SnapshotDescription getResult() {
		return result;
	}

	public SnapshotCreateResponse setResult(SnapshotDescription result) {
		this.result = result;
		return this;
	}
}
