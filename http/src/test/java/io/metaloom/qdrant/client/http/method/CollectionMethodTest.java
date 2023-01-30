package io.metaloom.qdrant.client.http.method;

import static io.metaloom.qdrant.client.http.model.collection.config.Distance.EUCLID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.metaloom.qdrant.client.http.AbstractClientTest;
import io.metaloom.qdrant.client.http.model.collection.CollectionCreateRequest;
import io.metaloom.qdrant.client.http.model.collection.CollectionListResponse;
import io.metaloom.qdrant.client.http.model.collection.CollectionResponse;
import io.metaloom.qdrant.client.http.model.collection.CollectionStatus;
import io.metaloom.qdrant.client.http.model.collection.config.NamedVectorParams;
import io.metaloom.qdrant.client.http.model.collection.config.VectorParams;

public class CollectionMethodTest extends AbstractClientTest {

	@Test
	public void testCreateCollectionWithNamedVectorParams() throws Exception {
		CollectionCreateRequest request = new CollectionCreateRequest();
		NamedVectorParams params = new NamedVectorParams();
		params.put("test", VectorParams.of(4, EUCLID));
		params.put("test2", VectorParams.of(4, EUCLID));
		request.setVectors(params);
		invoke(client.createCollection(TEST_COLLECTION_NAME, request));
	}

	@Test
	public void testCreateCollectionWithUnnamedVectorParams() throws Exception {
		CollectionCreateRequest request = new CollectionCreateRequest();
		request.setVectors(VectorParams.of(4, EUCLID));
		invoke(client.createCollection(TEST_COLLECTION_NAME, request));
	}

	@Test
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
	public void testGetCollectionInfo() throws Exception {
		createCollection();

		CollectionResponse response = invoke(client.loadCollection(TEST_COLLECTION_NAME));
		assertEquals(0, response.getResult().getPointsCount());
		assertEquals(0, response.getResult().getIndexedVectorsCount());
		assertEquals(0, response.getResult().getVectorsCount());
		assertEquals(CollectionStatus.GREEN, response.getResult().getStatus());
	}

	@Test
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
