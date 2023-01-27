package io.metaloom.qdrant.client.http.model;

import org.junit.Test;

import io.metaloom.qdrant.client.http.model.collection.CollectionUpdateAliasesRequest;

public class CollectionModelTest extends AbstractModelTest {

	@Test
	public void testCollectionAliasUpdateRequestModel() { 
		CollectionUpdateAliasesRequest request = load("collection/collection-alias-update-request", CollectionUpdateAliasesRequest.class);
	}
}
