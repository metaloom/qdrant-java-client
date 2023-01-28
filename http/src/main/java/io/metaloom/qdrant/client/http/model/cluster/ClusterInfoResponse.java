package io.metaloom.qdrant.client.http.model.cluster;

import io.metaloom.qdrant.client.http.model.AbstractResponse;
import io.metaloom.qdrant.client.http.model.collection.config.CollectionClusterInfo;

public class ClusterInfoResponse extends AbstractResponse {

	private CollectionClusterInfo result;

	public CollectionClusterInfo getResult() {
		return result;
	}

	public ClusterInfoResponse setResult(CollectionClusterInfo result) {
		this.result = result;
		return this;
	}
}
