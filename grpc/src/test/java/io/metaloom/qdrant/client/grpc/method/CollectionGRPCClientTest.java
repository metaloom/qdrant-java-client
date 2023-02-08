package io.metaloom.qdrant.client.grpc.method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.metaloom.qdrant.client.grpc.AbstractGRPCClientTest;
import io.metaloom.qdrant.client.grpc.proto.Collections.CollectionDescription;
import io.metaloom.qdrant.client.grpc.proto.Collections.Distance;
import io.metaloom.qdrant.client.grpc.proto.Collections.GetCollectionInfoResponse;
import io.metaloom.qdrant.client.grpc.proto.Collections.VectorParams;
import io.metaloom.qdrant.client.grpc.proto.Collections.VectorParamsMap;
import io.metaloom.qdrant.client.grpc.proto.Collections.VectorsConfig;
import io.metaloom.qdrant.client.testcases.CollectionClientTestcases;

public class CollectionGRPCClientTest extends AbstractGRPCClientTest implements CollectionClientTestcases {

	@Test
	@Override
	public void testCreateCollectionWithNamedVectorParams() throws Exception {
		VectorParams params = VectorParams.newBuilder()
			.setSize(4)
			.setDistance(Distance.Euclid)
			.build();

		// Add the params to a map
		VectorParamsMap paramsMap = VectorParamsMap.newBuilder().putMap("colors", params).build();

		// Create new collections
		assertTrue(client.createCollection(TEST_COLLECTION_NAME, paramsMap).sync().getResult());

		// Assert that the collection contains the colors parameters
		for (CollectionDescription collection : client.listCollections().sync().getCollectionsList()) {
			System.out.println(collection.getName());
			GetCollectionInfoResponse info = client.loadCollections(collection.getName()).sync();
			VectorsConfig config = info.getResult().getConfig().getParams().getVectorsConfig();
			assertTrue("The config did not contain the colors vector parameters.", config.getParamsMap().containsMap("colors"));
		}
	}

	@Test
	@Override
	public void testCreateCollectionWithUnnamedVectorParams() throws Exception {
		VectorParams params = VectorParams.newBuilder()
			.setSize(4)
			.setDistance(Distance.Euclid)
			.build();

		// Create new collections
		assertTrue(client.createCollection(TEST_COLLECTION_NAME, params).sync().getResult());

		// Assert that the collection contains the colors parameters
		for (CollectionDescription collection : client.listCollections().sync().getCollectionsList()) {
			System.out.println(collection.getName());
			GetCollectionInfoResponse info = client.loadCollections(collection.getName()).sync();
			VectorsConfig config = info.getResult().getConfig().getParams().getVectorsConfig();
			assertFalse("The config should not contain a params map.", config.hasParamsMap());
		}
	}

	@Test
	@Override
	public void testListCollections() throws Exception {
		createCollection(TEST_COLLECTION_NAME);
		assertEquals(1, client.listCollections().sync().getCollectionsCount());
	}
	
	@Test
	@Override
	public void testListCollectionAliases() throws Exception {
		createCollection(TEST_COLLECTION_NAME);
		client.listCollectionAliases(TEST_COLLECTION_NAME).sync();
		
	}

	@Test
	@Override
	public void testGetCollectionInfo() throws Exception {
		createCollection(TEST_COLLECTION_NAME);
		assertEquals(1, client.listCollections().sync().getCollectionsCount());

		GetCollectionInfoResponse info = client.loadCollections(TEST_COLLECTION_NAME).sync();
		assertEquals("Could not load correct distance from collection", Distance.Euclid,
			info.getResult().getConfig().getParams().getVectorsConfig().getParams().getDistance());
		assertEquals("Could not load correct dimension from collection", 4,
			info.getResult().getConfig().getParams().getVectorsConfig().getParams().getSize());
	}

	@Test
	@Override
	public void testDeleteCollection() throws Exception {
		createCollection(TEST_COLLECTION_NAME);
		assertEquals(1, client.listCollections().sync().getCollectionsCount());
		client.deleteCollection(TEST_COLLECTION_NAME, 200).sync();
		assertEquals("The collection was not deleted.", 0, client.listCollections().sync().getCollectionsCount());
	}

}
