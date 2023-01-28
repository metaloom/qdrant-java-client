package io.metaloom.qdrant.client.http.model.collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class DeleteAlias implements RestModel {

	@JsonProperty("alias_name")
	private String aliasName;

	public String getAliasName() {
		return aliasName;
	}

	public DeleteAlias setAliasName(String aliasName) {
		this.aliasName = aliasName;
		return this;
	}

}
