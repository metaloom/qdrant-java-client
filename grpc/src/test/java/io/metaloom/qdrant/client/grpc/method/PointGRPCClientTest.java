package io.metaloom.qdrant.client.grpc.method;

import static io.metaloom.qdrant.client.grpc.ModelHelper.point;
import static io.metaloom.qdrant.client.grpc.ModelHelper.pointId;
import static io.metaloom.qdrant.client.grpc.ModelHelper.pointIds;
import static io.metaloom.qdrant.client.grpc.ModelHelper.value;
import static io.metaloom.qdrant.client.grpc.ModelHelper.withPayload;
import static io.metaloom.qdrant.client.grpc.ModelHelper.withVector;
import static io.metaloom.qdrant.client.util.VectorUtil.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.testcontainers.shaded.com.google.common.collect.Sets;

import io.metaloom.qdrant.client.grpc.AbstractGRPCClientTest;
import io.metaloom.qdrant.client.grpc.ModelHelper;
import io.metaloom.qdrant.client.grpc.proto.JsonWithInt.Value;
import io.metaloom.qdrant.client.grpc.proto.Points.GetResponse;
import io.metaloom.qdrant.client.grpc.proto.Points.PointStruct;
import io.metaloom.qdrant.client.grpc.proto.Points.PointsOperationResponse;
import io.metaloom.qdrant.client.grpc.proto.Points.RecommendBatchResponse;
import io.metaloom.qdrant.client.grpc.proto.Points.RecommendPoints;
import io.metaloom.qdrant.client.grpc.proto.Points.RecommendResponse;
import io.metaloom.qdrant.client.grpc.proto.Points.RetrievedPoint;
import io.metaloom.qdrant.client.grpc.proto.Points.ScoredPoint;
import io.metaloom.qdrant.client.grpc.proto.Points.ScrollResponse;
import io.metaloom.qdrant.client.grpc.proto.Points.SearchBatchResponse;
import io.metaloom.qdrant.client.grpc.proto.Points.SearchPoints;
import io.metaloom.qdrant.client.grpc.proto.Points.SearchResponse;
import io.metaloom.qdrant.client.grpc.proto.Points.UpdateStatus;
import io.metaloom.qdrant.client.grpc.proto.Points.Vector;
import io.metaloom.qdrant.client.grpc.proto.Points.Vectors;
import io.metaloom.qdrant.client.testcases.PointClientTestcases;

public class PointGRPCClientTest extends AbstractGRPCClientTest implements PointClientTestcases {

	@Before
	public void setupTestData() {
		createCollection(TEST_COLLECTION_NAME);

		Map<String, Value> values = new HashMap<>();
		values.put("color", value("blue"));
		PointStruct p1 = point(42L, new float[] { 7.43f, 0.1f, 0.25f, 1.5f }, values);
		PointStruct p2 = point(43L, new float[] { 0.45f, 2.61f, 0.88f, 6.25f }, values);
		PointStruct p3 = point(44L, new float[] { 2.41f, 0.9f, 0.81f, 2.45f }, values);
		PointStruct p4 = point(45L, new float[] { 0.42f, 1.0f, 0.51f, 5.85f }, values);

		client.upsertPoint(TEST_COLLECTION_NAME, p1, true).sync();
		client.upsertPoint(TEST_COLLECTION_NAME, p2, true).sync();
		client.upsertPoint(TEST_COLLECTION_NAME, p3, true).sync();
		client.upsertPoint(TEST_COLLECTION_NAME, p4, true).sync();

		assertPointCount(4, TEST_COLLECTION_NAME);

	}

	@Test
	@Override
	public void testGetPoint() throws Exception {
		GetResponse response = client.getPoint(TEST_COLLECTION_NAME, pointId(42L)).sync();
		assertEquals(1, response.getResultCount());
	}

	@Test
	@Override
	public void testGetPoints() throws Exception {
		GetResponse list = client.getPoints(TEST_COLLECTION_NAME, withPayload(), withVector(), pointId(42L), pointId(43L)).sync();
		assertEquals(2, list.getResultCount());
	}

	@Test
	@Override
	public void testUpsertPointViaList() throws Exception {
		float[] vec = new float[] { 0, 0, 0, 0 };
		List<PointStruct> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(point(80L + i, vec, null));
		}

		client.upsertPoints(TEST_COLLECTION_NAME, list, true).sync();
		assertPointCount(14, TEST_COLLECTION_NAME);
	}

	@Test
	public void testUpsertPointWithUuid() {
		PointStruct point = point(UUID.randomUUID(), new float[] { 0.2f, 0.1f, 0.3f, 0.4f }, null);
		client.upsertPoint(TEST_COLLECTION_NAME, point, true).sync();
		assertPointCount(4 + 1, TEST_COLLECTION_NAME);
	}

	@Test
	@Override
	@Ignore("Not supported via gRPC")
	public void testUpsertPointsViaListBatch() throws Exception {
	}

	@Test
	@Override
	@Ignore("Not supported via gRPC")
	public void testUpsertPointsViaNamedBatch() throws Exception {
	}

	@Test
	@Override
	public void testDeletePoints() throws Exception {
		assertPointCount(4, TEST_COLLECTION_NAME);
		client.deletePoints(TEST_COLLECTION_NAME, true, pointId(42L)).sync();
		assertPointCount(3, TEST_COLLECTION_NAME);
	}

	@Test
	@Override
	public void testSetPointPayload() throws Exception {
		Map<String, Value> newPayload = new HashMap<>();
		newPayload.put("test123", value("the-value"));
		PointsOperationResponse response = client.setPointPayload(TEST_COLLECTION_NAME, true, null, newPayload, pointId(42L)).sync();
		assertEquals(UpdateStatus.Completed, response.getResult().getStatus());

		Map<String, Value> payload = client.getPoint(TEST_COLLECTION_NAME, pointId(42L)).sync().getResult(0).getPayloadMap();
		assertEquals(2, payload.size());
		assertEquals("the-value", payload.get("test123").getStringValue());
	}

	@Test
	@Override
	public void testOverwritePointPayload() throws Exception {
		Map<String, Value> newPayload = new HashMap<>();
		newPayload.put("test123", value("the-value"));
		PointsOperationResponse response = client.overwritePayload(TEST_COLLECTION_NAME, true, null, newPayload, pointId(42L)).sync();
		assertEquals(UpdateStatus.Completed, response.getResult().getStatus());

		Map<String, Value> payload = client.getPoint(TEST_COLLECTION_NAME, pointId(42L)).sync().getResult(0).getPayloadMap();
		// assertEquals(1, payload.size());
		assertEquals("the-value", payload.get("test123").getStringValue());
	}

	@Test
	@Override
	public void testDeletePointPayload() throws Exception {
		fail("insert another payload key");
		HashSet<String> keys = Sets.newHashSet("colors");
		PointsOperationResponse response = client.deletePayload(TEST_COLLECTION_NAME, true, keys, null, pointId(42L)).sync();
		assertEquals(UpdateStatus.Completed, response.getResult().getStatus());
		assertTrue("There should not be any payload values",
			client.getPoint(TEST_COLLECTION_NAME, pointId(42)).sync().getResult(0).getPayloadMap().isEmpty());
	}

	@Test
	@Override
	public void testClearPointPayload() throws Exception {
		GetResponse before = client.getPoint(TEST_COLLECTION_NAME, withPayload(), withVector(), pointId(42L)).sync();
		assertFalse("The map should not be empty", before.getResultList().get(0).getPayloadMap().isEmpty());

		PointsOperationResponse response = client.clearPayload(TEST_COLLECTION_NAME, true, null, pointId(42L)).sync();
		assertEquals(UpdateStatus.Completed, response.getResult().getStatus());

		GetResponse after = client.getPoint(TEST_COLLECTION_NAME, withPayload(), withVector(), pointId(42L)).sync();
		assertTrue("The map should have been cleared", after.getResultList().get(0).getPayloadMap().isEmpty());
	}

	@Test
	@Override
	public void testScrollPoints() throws Exception {
		ScrollResponse scrollResponse = client.scrollPoint(TEST_COLLECTION_NAME, null, 4, null, null, null).sync();
		List<RetrievedPoint> list = scrollResponse.getResultList();
		assertEquals(4, list.size());
		assertNotNull(scrollResponse.getNextPageOffset());
	}

	@Test
	@Override
	public void testSearchPoints() throws Exception {
		float[] vector = new float[] { 2.41f, 0.9f, 0.81f, 2.45f };
		SearchResponse response = client.searchPoints(TEST_COLLECTION_NAME, vector, 2, 100f).sync();
		List<ScoredPoint> list = response.getResultList();
		assertFalse(list.isEmpty());
		assertEquals("The first result should be exactly 0 scrore since it used the stored vector of a point for the search", 0f,
			list.get(0).getScore(), 0f);
		assertEquals("The result was not limited to two results.", 2, list.size());
	}

	@Test
	@Override
	public void testSearchBatchPoints() throws Exception {
		List<SearchPoints> searches = new ArrayList<>();

		List<Float> vectorList = toList(0f, 0f, 0f, 0f);

		SearchPoints.Builder request = SearchPoints.newBuilder()
			.setLimit(10)
			.addAllVector(vectorList)
			.setCollectionName(TEST_COLLECTION_NAME);

		searches.add(request.build());

		SearchBatchResponse responses = client.searchBatch(TEST_COLLECTION_NAME, searches).sync();
		assertEquals(1, responses.getResultList().size());
	}

	@Test
	@Override
	public void testRecommendPoints() throws Exception {
		RecommendResponse result = client.recommendPoints(TEST_COLLECTION_NAME, pointIds(42L), 2, null).sync();
		List<ScoredPoint> list = result.getResultList();
		assertFalse(list.isEmpty());
		assertEquals("The result was not limited to two results.", 2, list.size());
	}

	@Test
	@Override
	public void testRecommendBatchPoints() throws Exception {
		List<RecommendPoints> searches = new ArrayList<>();

		RecommendPoints.Builder request = RecommendPoints.newBuilder()
			.setCollectionName(TEST_COLLECTION_NAME)
			.addAllPositive(pointIds(42L))
			.setLimit(10);

		searches.add(request.build());

		RecommendBatchResponse response = client.recommendBatchPoints(TEST_COLLECTION_NAME, searches).sync();
		assertEquals(1, response.getResultCount());
	}

	@Test
	@Override
	public void testCountPoints() throws Exception {
		// Insert a new vector
		for (int i = 0; i < 10; i++) {
			Vector vector = ModelHelper.vector(new float[] { 0.43f + i, 0.1f, 0.61f, 1.45f });
			PointStruct point = PointStruct.newBuilder()
				.putPayload("color", ModelHelper.value("blue"))
				.setId(ModelHelper.pointId(42L + i))
				.setVectors(Vectors.newBuilder().setVector(vector))
				.build();
			assertEquals(UpdateStatus.Completed, client.upsertPoint(TEST_COLLECTION_NAME, point, true).sync().getResult().getStatus());
		}
		assertPointCount(4 + 10, TEST_COLLECTION_NAME);
	}

}
