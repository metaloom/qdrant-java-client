package io.metaloom.qdrant.client.http.method;

import static io.metaloom.qdrant.client.http.model.collection.config.Distance.EUCLID;

import org.junit.Test;

import io.metaloom.qdrant.client.http.AbstractClientTest;
import io.metaloom.qdrant.client.http.model.collection.CollectionCreateRequest;
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
		invoke(client.createCollection(TEST_COLLECTION_NAME + "_1", request));
	}

	@Test
	public void tetCreateCollectionWithUnnamedVectorParams() throws Exception {
		CollectionCreateRequest request = new CollectionCreateRequest();
		request.setVectors(VectorParams.of(4, EUCLID));
		invoke(client.createCollection(TEST_COLLECTION_NAME + "_2", request));
	}
}
