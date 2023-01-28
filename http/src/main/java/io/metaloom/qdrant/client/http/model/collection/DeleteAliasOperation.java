package io.metaloom.qdrant.client.http.model.collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteAliasOperation implements AliasOperation {

	@JsonProperty("delete_alias")
	private DeleteAlias deleteAlias;

	public DeleteAlias getDeleteAlias() {
		return deleteAlias;
	}

	public DeleteAliasOperation setDeleteAlias(DeleteAlias deleteAlias) {
		this.deleteAlias = deleteAlias;
		return this;
	}
}
