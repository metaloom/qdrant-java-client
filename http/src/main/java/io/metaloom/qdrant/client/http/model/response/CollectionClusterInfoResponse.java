package io.metaloom.qdrant.client.http.model.response;

import io.metaloom.qdrant.client.http.model.collection.config.CollectionClusterInfo;

public class CollectionClusterInfoResponse extends AbstractResponse {

	private CollectionClusterInfo result;

	public CollectionClusterInfo getResult() {
		return result;
	}
}
