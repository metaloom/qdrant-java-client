package io.metaloom.qdrant.client.http.model.collection;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class CollectionListResponse extends AbstractResponse {

	private CollectionsResponse result;

	public CollectionsResponse getResult() {
		return result;
	}

	public CollectionListResponse setResult(CollectionsResponse result) {	
		this.result = result;
		return this;
	}
}
