package io.metaloom.qdrant.client.http.model.collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class CreateAlias implements RestModel {

	@JsonProperty("collection_name")
	private String collectionName;

	@JsonProperty("alias_name")
	private String aliasName;

	public String getCollectionName() {
		return collectionName;
	}

	public CreateAlias setCollectionName(String collectionName) {
		this.collectionName = collectionName;
		return this;
	}

	public String getAliasName() {
		return aliasName;
	}

	public CreateAlias setAliasName(String aliasName) {
		this.aliasName = aliasName;
		return this;
	}

}
