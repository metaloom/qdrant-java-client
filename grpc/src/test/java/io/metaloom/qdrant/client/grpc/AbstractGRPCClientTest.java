package io.metaloom.qdrant.client.grpc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.google.protobuf.GeneratedMessageV3;

import io.metaloom.qdrant.client.AbstractContainerTest;
import io.metaloom.qdrant.client.grpc.method.GrpcClientRequest;
import io.metaloom.qdrant.client.grpc.proto.Collections.AliasOperations;
import io.metaloom.qdrant.client.grpc.proto.Collections.CreateAlias;
import io.metaloom.qdrant.client.grpc.proto.Collections.Distance;
import io.metaloom.qdrant.client.grpc.proto.Collections.VectorParams;
import io.metaloom.qdrant.client.grpc.proto.Collections.VectorParamsMap;

public abstract class AbstractGRPCClientTest extends AbstractContainerTest {

	public static final String TEST_COLLECTION_NAME = "the-test-collection";

	public static final String TEST_VECTOR_NAME = "color";

	public static final String TEST_VECTOR_NAME_2 = "color-2";

	public static final String TEST_ALIAS_NAME = "new-alias-name";

	protected QDrantGRPCClient client;

	@BeforeEach
	public void setupClient() {
		client = QDrantGRPCClient.builder()
			.setHostname(qdrant.getHost())
			.setPort(qdrant.grpcPort())
			.build();
	}

	@AfterEach
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

		// Add the params to a map
		VectorParamsMap paramsMap = VectorParamsMap.newBuilder()
			.putMap(TEST_VECTOR_NAME, params)
			.putMap(TEST_VECTOR_NAME_2, params)
			.build();

		// Create new collections
		assertTrue(client.createCollection(collectionName, paramsMap).sync().getResult());
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
