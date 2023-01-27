package io.metaloom.qdrant.client.http.model.request;

import java.util.List;

import io.metaloom.qdrant.client.http.model.collection.AliasOperations;

public class CollectionUpdateAliasesRequest {

	List<? extends AliasOperations> actions;
}
