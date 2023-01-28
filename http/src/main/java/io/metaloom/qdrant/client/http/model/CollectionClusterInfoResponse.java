package io.metaloom.qdrant.client.http.model;

import io.metaloom.qdrant.client.http.model.collection.config.CollectionClusterInfo;

public class CollectionClusterInfoResponse extends AbstractResponse {

	private CollectionClusterInfo result;

	public CollectionClusterInfo getResult() {
		return result;
	}

	public CollectionClusterInfoResponse setResult(CollectionClusterInfo result) {
		this.result = result;
		return this;
	}
}
