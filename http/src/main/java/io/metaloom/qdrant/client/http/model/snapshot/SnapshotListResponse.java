package io.metaloom.qdrant.client.http.model.snapshot;

import java.util.List;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class SnapshotListResponse extends AbstractResponse {

	private List<SnapshotDescription> result;

	public List<SnapshotDescription> getResult() {
		return result;
	}
}
