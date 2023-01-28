package io.metaloom.qdrant.client.http.model.collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class RenameAlias implements RestModel {

	@JsonProperty("old_alias_name")
	private String oldAliasName;

	@JsonProperty("new_alias_name")
	private String newAliasName;

	public String getOldAliasName() {
		return oldAliasName;
	}

	public RenameAlias setOldAliasName(String oldAliasName) {
		this.oldAliasName = oldAliasName;
		return this;
	}

	public String getNewAliasName() {
		return newAliasName;
	}

	public RenameAlias setNewAliasName(String newAliasName) {
		this.newAliasName = newAliasName;
		return this;
	}
}
