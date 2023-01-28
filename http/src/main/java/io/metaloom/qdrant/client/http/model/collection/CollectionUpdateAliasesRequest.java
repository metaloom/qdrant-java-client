package io.metaloom.qdrant.client.http.model.collection;

import java.util.List;

import io.metaloom.qdrant.client.http.model.RestRequestModel;

public class CollectionUpdateAliasesRequest implements RestRequestModel {

	private List<? extends AliasOperations> actions;

	public List<? extends AliasOperations> getActions() {
		return actions;
	}
}
