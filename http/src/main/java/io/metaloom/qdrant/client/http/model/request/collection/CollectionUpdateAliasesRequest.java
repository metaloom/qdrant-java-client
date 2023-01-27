package io.metaloom.qdrant.client.http.model.request.collection;

import java.util.List;

import io.metaloom.qdrant.client.http.model.collection.AliasOperations;

public class CollectionUpdateAliasesRequest {

	List<? extends AliasOperations> actions;
}
