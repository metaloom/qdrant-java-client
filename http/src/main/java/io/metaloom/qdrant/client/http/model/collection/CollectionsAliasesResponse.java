package io.metaloom.qdrant.client.http.model.collection;

import java.util.List;

import io.metaloom.qdrant.client.http.model.RestModel;

public class CollectionsAliasesResponse implements RestModel {

	private List<AliasDescription> aliases;

	public List<AliasDescription> getAliases() {
		return aliases;
	}

	public CollectionsAliasesResponse setAliases(List<AliasDescription> aliases) {
		this.aliases = aliases;
		return this;
	}
}
