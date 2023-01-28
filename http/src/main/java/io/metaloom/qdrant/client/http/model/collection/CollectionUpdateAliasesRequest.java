package io.metaloom.qdrant.client.http.model.collection;

import java.util.List;

import io.metaloom.qdrant.client.http.model.RestRequestModel;

public class CollectionUpdateAliasesRequest implements RestRequestModel {

	private List<AliasOperation> actions;

	public List<AliasOperation> getActions() {
		return actions;
	}

	public CollectionUpdateAliasesRequest setActions(List<AliasOperation> actions) {
		this.actions = actions;
		return this;
	}
}
