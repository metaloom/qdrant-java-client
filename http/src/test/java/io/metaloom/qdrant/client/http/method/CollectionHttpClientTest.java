package io.metaloom.qdrant.client.http.method;

import static io.metaloom.qdrant.client.http.model.collection.config.Distance.EUCLID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import io.metaloom.qdrant.client.http.AbstractHTTPClientTest;
import io.metaloom.qdrant.client.http.model.collection.AliasDescription;
import io.metaloom.qdrant.client.http.model.collection.CollectionCreateRequest;
import io.metaloom.qdrant.client.http.model.collection.CollectionListResponse;
import io.metaloom.qdrant.client.http.model.collection.CollectionResponse;
import io.metaloom.qdrant.client.http.model.collection.CollectionStatus;
import io.metaloom.qdrant.client.http.model.collection.CollectionUpdateAliasesRequest;
import io.metaloom.qdrant.client.http.model.collection.config.NamedVectorParams;
import io.metaloom.qdrant.client.http.model.collection.config.VectorParams;
import io.metaloom.qdrant.client.testcases.CollectionClientTestcases;

public class CollectionHttpClientTest extends AbstractHTTPClientTest implements CollectionClientTestcases {

	@Test
	@Override
	public void testCreateCollectionWithNamedVectorParams() throws Exception {
		CollectionCreateRequest request = new CollectionCreateRequest();
		NamedVectorParams params = new NamedVectorParams();
		params.put("test", VectorParams.of(4, EUCLID));
		params.put("test2", VectorParams.of(4, EUCLID));
		request.setVectors(params);
		invoke(client.createCollection(TEST_COLLECTION_NAME, request));
	}

	@Test
	@Override
	public void testCreateCollectionWithUnnamedVectorParams() throws Exception {
		CollectionCreateRequest request = new CollectionCreateRequest();
		request.setVectors(VectorParams.of(4, EUCLID));
		invoke(client.createCollection(TEST_COLLECTION_NAME, request));
	}

	@Test
	@Ignore
	public void testCreateMultipleCollections() throws Exception {
		CollectionCreateRequest request = new CollectionCreateRequest();
		request.setVectors(VectorParams.of(4, EUCLID));
		for (int i = 0; i < 10; i++) {
			invoke(client.createCollection(TEST_COLLECTION_NAME, request));
		}
		assertEquals("There should still be only one collection", 1, invoke(client.listCollections()).getResult().getCollections().size());
	}

	@Test
	@Override
	public void testListCollections() throws Exception {
		// Assert initial status
		CollectionListResponse response = invoke(client.listCollections());
		assertTrue("There should not be any collection", response.getResult().getCollections().isEmpty());

		// Now create collection
		createCollection();

		CollectionListResponse response2 = invoke(client.listCollections());
		assertEquals(1, response2.getResult().getCollections().size());
	}

	@Test
	@Override
	public void testListCollectionAliases() throws Exception {
		createCollection();
		assertTrue(invoke(client.listCollectionAliases(TEST_COLLECTION_NAME)).getResult().getAliases().isEmpty());
		CollectionUpdateAliasesRequest request = new CollectionUpdateAliasesRequest();
		request.addCreateAlias(TEST_COLLECTION_NAME, "new-alias");
		invoke(client.updateCollectionAliases(TEST_COLLECTION_NAME, request, 1000));

		List<AliasDescription> list = invoke(client.listCollectionAliases(TEST_COLLECTION_NAME)).getResult().getAliases();
		assertEquals("Got one alias", 1, list.size());
		assertEquals("new-alias", list.get(0).getAliasName());
	}

	@Test
	@Override
	public void testUpdateCollectionAliases() throws Exception {
		createCollection();

		CollectionUpdateAliasesRequest request = new CollectionUpdateAliasesRequest();
		request.addCreateAlias(TEST_COLLECTION_NAME, "new-alias");
		invoke(client.updateCollectionAliases(TEST_COLLECTION_NAME, request, 1000));
	}

	@Test
	@Override
	public void testGetCollectionInfo() throws Exception {
		createCollection();

		CollectionResponse response = invoke(client.loadCollection(TEST_COLLECTION_NAME));
		assertEquals(0, response.getResult().getPointsCount());
		assertEquals(0, response.getResult().getIndexedVectorsCount());
		assertEquals(0, response.getResult().getVectorsCount());
		assertEquals(CollectionStatus.GREEN, response.getResult().getStatus());
	}

	@Test
	@Override
	public void testDeleteCollection() throws Exception {
		createCollection();
		assertFalse("There should be at least one collection", invoke(client.listCollections()).getResult().getCollections().isEmpty());
		invoke(client.deleteCollection(TEST_COLLECTION_NAME, 1000));
		assertTrue("There should be no collections anymore", invoke(client.listCollections()).getResult().getCollections().isEmpty());
	}

	private void createCollection() throws Exception {
		CollectionCreateRequest request = new CollectionCreateRequest();
		request.setVectors(VectorParams.of(4, EUCLID));
		invoke(client.createCollection(TEST_COLLECTION_NAME, request));
	}

}
