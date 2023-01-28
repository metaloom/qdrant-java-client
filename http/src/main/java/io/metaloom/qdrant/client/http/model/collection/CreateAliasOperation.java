package io.metaloom.qdrant.client.http.model.collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateAliasOperation implements AliasOperation {

	@JsonProperty("create_alias")
	private CreateAlias createAlias;

	public CreateAlias getCreateAlias() {
		return createAlias;
	}

	public CreateAliasOperation setCreateAlias(CreateAlias createAlias) {
		this.createAlias = createAlias;
		return this;
	}
}
