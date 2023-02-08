package io.metaloom.qdrant.client.grpc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;

import com.google.protobuf.GeneratedMessageV3;

import io.metaloom.qdrant.client.AbstractContainerTest;
import io.metaloom.qdrant.client.grpc.method.GrpcClientRequest;
import io.metaloom.qdrant.client.grpc.proto.Collections.AliasOperations;
import io.metaloom.qdrant.client.grpc.proto.Collections.CreateAlias;
import io.metaloom.qdrant.client.grpc.proto.Collections.Distance;
import io.metaloom.qdrant.client.grpc.proto.Collections.VectorParams;

public abstract class AbstractGRPCClientTest extends AbstractContainerTest {

	public static final String TEST_COLLECTION_NAME = "the-test-collection";

	public static final String TEST_ALIAS_NAME = "new-alias-name";

	protected QDrantGRPCClient client;

	@Before
	public void setupClient() {
		client = QDrantGRPCClient.builder()
			.setHostname("localhost")
			.setPort(qdrant.grpcPort())
			.build();
	}

	@After
	public void closeClient() {
		if (client != null) {
			client.close();
		}
	}

	protected void createCollection(String collectionName) {
		VectorParams params = VectorParams.newBuilder()
			.setSize(4)
			.setDistance(Distance.Euclid)
			.build();

		// Create new collections
		assertTrue(client.createCollection(collectionName, params).sync().getResult());
	}

	protected void createAlias(String collectionName, String aliasName) {
		CreateAlias alias = CreateAlias.newBuilder()
			.setCollectionName(collectionName)
			.setAliasName(aliasName)
			.build();
		AliasOperations newAliasAction = AliasOperations.newBuilder()
			.setCreateAlias(alias).build();
		assertTrue(client.updateCollectionAliases(1000, newAliasAction).sync().getResult());
	}

	protected <T extends GeneratedMessageV3> T invoke(GrpcClientRequest<T> request) throws Exception {
		try {
			T response = request.sync();
			// assertSuccess((AbstractResponse) response);
			return response;
		} catch (Exception e) {
			fail("Request failed with error " + e.getMessage());
			return null;
		}
	}

	protected void assertPointCount(int expectedCount, String collectionName) {
		assertEquals("The collection " + collectionName + " did not contain the expected point count.", expectedCount,
			client.countPoints(collectionName, null, true).sync().getResult().getCount());

	}
}
