package io.metaloom.qdrant.client.http.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.metaloom.qdrant.client.http.model.collection.CollectionUpdateAliasesRequest;
import io.metaloom.qdrant.client.http.model.collection.CreateAliasOperation;

public class CollectionModelTest extends AbstractModelTest {

	@Test
	public void testCollectionAliasUpdateRequestModel() {
		CollectionUpdateAliasesRequest request = load("collection/collection-alias-update-request", CollectionUpdateAliasesRequest.class);
		CreateAliasOperation createAlias = (CreateAliasOperation) request.getActions().get(0);
		assertEquals("theCollectionName", createAlias.getCreateAlias().getCollectionName());
		assertEquals("theAlias", createAlias.getCreateAlias().getAliasName());
	}
}
