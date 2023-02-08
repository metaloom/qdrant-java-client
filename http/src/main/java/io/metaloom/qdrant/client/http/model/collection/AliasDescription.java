package io.metaloom.qdrant.client.http.model.collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class AliasDescription implements RestModel {

	@JsonProperty("alias_name")
	private String aliasName;

	@JsonProperty("collection_name")
	private String collectionName;

	public String getAliasName() {
		return aliasName;
	}

	public AliasDescription setAliasName(String aliasName) {
		this.aliasName = aliasName;
		return this;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public AliasDescription setCollectionName(String collectionName) {
		this.collectionName = collectionName;
		return this;
	}
}
