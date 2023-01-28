package io.metaloom.qdrant.client.http.model.collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RenameAliasOperation implements AliasOperation {

	@JsonProperty("rename_alias")
	private RenameAlias renameAlias;

	public RenameAlias getRenameAlias() {
		return renameAlias;
	}

	public RenameAliasOperation setRenameAlias(RenameAlias renameAlias) {
		this.renameAlias = renameAlias;
		return this;
	}
}
