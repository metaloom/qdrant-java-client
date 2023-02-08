package io.metaloom.qdrant.client.http.model.collection;

import io.metaloom.qdrant.client.http.model.AbstractResponse;

public class CollectionsAliasesListResponse extends AbstractResponse {

	private CollectionsAliasesResponse result;

	public CollectionsAliasesResponse getResult() {
		return result;
	}

	public CollectionsAliasesListResponse setResult(CollectionsAliasesResponse result) {	
		this.result = result;
		return this;
	}
}
