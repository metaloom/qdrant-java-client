package io.metaloom.qdrant.client.grpc.method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.metaloom.qdrant.client.grpc.AbstractGRPCClientTest;
import io.metaloom.qdrant.client.grpc.proto.Collections.AliasDescription;
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
		VectorParamsMap paramsMap = VectorParamsMap.newBuilder()
			.putMap(TEST_VECTOR_NAME, params)
			.putMap(TEST_VECTOR_NAME_2, params)
			.build();

		// Create new collections
		assertTrue(client.createCollection(TEST_COLLECTION_NAME, paramsMap).sync().getResult());

		// Assert that the collection contains the colors parameters
		for (CollectionDescription collection : client.listCollections().sync().getCollectionsList()) {
			System.out.println(collection.getName());
			GetCollectionInfoResponse info = client.loadCollections(collection.getName()).sync();
			VectorsConfig config = info.getResult().getConfig().getParams().getVectorsConfig();
			assertTrue("The config did not contain the colors vector parameters.", config.getParamsMap().containsMap(TEST_VECTOR_NAME));
			assertTrue("The config did not contain the colors-2 vector parameters.", config.getParamsMap().containsMap(TEST_VECTOR_NAME_2));
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
		assertEquals(0, client.listCollectionAliases(TEST_COLLECTION_NAME).sync().getAliasesCount());
		createAlias(TEST_COLLECTION_NAME, TEST_ALIAS_NAME);
		List<AliasDescription> aliases = client.listCollectionAliases(TEST_COLLECTION_NAME).sync().getAliasesList();
		assertEquals(TEST_ALIAS_NAME, aliases.get(0).getAliasName());
		assertEquals(TEST_COLLECTION_NAME, aliases.get(0).getCollectionName());
	}

	@Test
	@Override
	public void testUpdateCollectionAliases() throws Exception {
		createCollection(TEST_COLLECTION_NAME);
		createAlias(TEST_COLLECTION_NAME, TEST_ALIAS_NAME);
		List<AliasDescription> aliases = client.listCollectionAliases(TEST_COLLECTION_NAME).sync().getAliasesList();
		assertEquals(1, aliases.size());
		assertEquals(TEST_ALIAS_NAME, aliases.get(0).getAliasName());
		assertEquals(TEST_COLLECTION_NAME, aliases.get(0).getCollectionName());
	}

	@Test
	@Override
	public void testGetCollectionInfo() throws Exception {
		createCollection(TEST_COLLECTION_NAME);
		assertEquals(1, client.listCollections().sync().getCollectionsCount());

		GetCollectionInfoResponse info = client.loadCollections(TEST_COLLECTION_NAME).sync();
		assertEquals("Could not load correct distance from collection", Distance.Euclid,
			info.getResult().getConfig().getParams().getVectorsConfig().getParamsMap().getMapMap().get(TEST_VECTOR_NAME).getDistance());
		assertEquals("Could not load correct dimension from collection", 4,
			info.getResult().getConfig().getParams().getVectorsConfig().getParamsMap().getMapMap().get(TEST_VECTOR_NAME).getSize());
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
