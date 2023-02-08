package io.metaloom.qdrant.client.http.model.collection;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@JsonIgnore
	public CollectionUpdateAliasesRequest addCreateAlias(String collectionName, String aliasName) {
		if (this.actions == null) {
			this.actions = new ArrayList<>();
		}
		CreateAlias alias = new CreateAlias();
		alias.setCollectionName(collectionName);
		alias.setAliasName(aliasName);
		this.actions.add(new CreateAliasOperation().setCreateAlias(alias));
		return this;
	}
}
