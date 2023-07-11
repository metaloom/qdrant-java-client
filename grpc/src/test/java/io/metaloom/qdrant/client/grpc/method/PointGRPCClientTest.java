package io.metaloom.qdrant.client.grpc.method;

import static io.metaloom.qdrant.client.util.ModelHelper.namedPoint;
import static io.metaloom.qdrant.client.util.ModelHelper.pointId;
import static io.metaloom.qdrant.client.util.ModelHelper.pointIds;
import static io.metaloom.qdrant.client.util.ModelHelper.selectByIds;
import static io.metaloom.qdrant.client.util.ModelHelper.value;
import static io.metaloom.qdrant.client.util.ModelHelper.vectorList;
import static io.metaloom.qdrant.client.util.ModelHelper.withPayload;
import static io.metaloom.qdrant.client.util.ModelHelper.withVector;
import static io.metaloom.qdrant.client.util.VectorUtil.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.com.google.common.collect.Sets;

import io.metaloom.qdrant.client.grpc.AbstractGRPCClientTest;
import io.metaloom.qdrant.client.grpc.proto.JsonWithInt.Value;
import io.metaloom.qdrant.client.grpc.proto.Points.DeletePointVectors;
import io.metaloom.qdrant.client.grpc.proto.Points.GetResponse;
import io.metaloom.qdrant.client.grpc.proto.Points.NamedVectors;
import io.metaloom.qdrant.client.grpc.proto.Points.PointStruct;
import io.metaloom.qdrant.client.grpc.proto.Points.PointVectors;
import io.metaloom.qdrant.client.grpc.proto.Points.PointsOperationResponse;
import io.metaloom.qdrant.client.grpc.proto.Points.RecommendBatchResponse;
import io.metaloom.qdrant.client.grpc.proto.Points.RecommendPointGroups;
import io.metaloom.qdrant.client.grpc.proto.Points.RecommendPoints;
import io.metaloom.qdrant.client.grpc.proto.Points.RecommendResponse;
import io.metaloom.qdrant.client.grpc.proto.Points.RetrievedPoint;
import io.metaloom.qdrant.client.grpc.proto.Points.ScoredPoint;
import io.metaloom.qdrant.client.grpc.proto.Points.ScrollResponse;
import io.metaloom.qdrant.client.grpc.proto.Points.SearchBatchResponse;
import io.metaloom.qdrant.client.grpc.proto.Points.SearchPointGroups;
import io.metaloom.qdrant.client.grpc.proto.Points.SearchPoints;
import io.metaloom.qdrant.client.grpc.proto.Points.SearchResponse;
import io.metaloom.qdrant.client.grpc.proto.Points.UpdatePointVectors;
import io.metaloom.qdrant.client.grpc.proto.Points.UpdateStatus;
import io.metaloom.qdrant.client.grpc.proto.Points.Vectors;
import io.metaloom.qdrant.client.grpc.proto.Points.VectorsSelector;
import io.metaloom.qdrant.client.testcases.PointClientTestcases;
import io.metaloom.qdrant.client.util.ModelHelper;

public class PointGRPCClientTest extends AbstractGRPCClientTest implements PointClientTestcases {

	@BeforeEach
	public void setupTestData() {
		createCollection(TEST_COLLECTION_NAME);

		Map<String, Value> values = new HashMap<>();
		values.put(TEST_VECTOR_NAME, value("blue"));
		PointStruct p1 = namedPoint(pointId(42L), TEST_VECTOR_NAME, new float[] { 7.43f, 0.1f, 0.25f, 1.5f }, values);
		PointStruct p2 = namedPoint(pointId(43L), TEST_VECTOR_NAME, new float[] { 0.45f, 2.61f, 0.88f, 6.25f }, values);
		PointStruct p3 = namedPoint(pointId(44L), TEST_VECTOR_NAME, new float[] { 2.41f, 0.9f, 0.81f, 2.45f }, values);
		PointStruct p4 = namedPoint(pointId(45L), TEST_VECTOR_NAME, new float[] { 0.42f, 1.0f, 0.51f, 5.85f }, values);

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
			list.add(namedPoint(80L + i, TEST_VECTOR_NAME, vec, null));
		}

		client.upsertPoints(TEST_COLLECTION_NAME, list, true).sync();
		assertPointCount(14, TEST_COLLECTION_NAME);
	}

	@Test
	public void testUpsertPointWithUuid() {
		PointStruct point = namedPoint(pointId(UUID.randomUUID()), TEST_VECTOR_NAME, new float[] { 0.2f, 0.1f, 0.3f, 0.4f }, null);
		client.upsertPoint(TEST_COLLECTION_NAME, point, true).sync();
		assertPointCount(4 + 1, TEST_COLLECTION_NAME);
	}

	@Test
	@Override
	@Disabled("Not supported via gRPC")
	public void testUpsertPointsViaListBatch() throws Exception {
	}

	@Test
	@Override
	@Disabled("Not supported via gRPC")
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

		Map<String, Value> payload = client.getPoint(TEST_COLLECTION_NAME, true, false, pointId(42L)).sync().getResult(0).getPayloadMap();
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

		Map<String, Value> payload = client.getPoint(TEST_COLLECTION_NAME, true, false, pointId(42L)).sync().getResult(0).getPayloadMap();
		// assertEquals(1, payload.size());
		assertEquals("the-value", payload.get("test123").getStringValue());
	}

	@Test
	@Override
	public void testDeletePointPayload() throws Exception {
		final String extraKey = "test123";

		Map<String, Value> newPayload = new HashMap<>();
		newPayload.put(extraKey, value("the-value"));
		PointsOperationResponse response = client.setPointPayload(TEST_COLLECTION_NAME, true, null, newPayload, pointId(42L)).sync();
		assertEquals(UpdateStatus.Completed, response.getResult().getStatus());

		Map<String, Value> before = client.getPoint(TEST_COLLECTION_NAME, true, false, pointId(42L)).sync().getResult(0).getPayloadMap();
		assertEquals("There should be two props now.", 2, before.keySet().size());

		// Now delete the extra payload prop
		HashSet<String> keys = Sets.newHashSet(extraKey);
		PointsOperationResponse response2 = client.deletePayload(TEST_COLLECTION_NAME, true, keys, null, pointId(42L)).sync();
		assertEquals(UpdateStatus.Completed, response2.getResult().getStatus());

		// And assert the operation
		Map<String, Value> after = client.getPoint(TEST_COLLECTION_NAME, true, false, pointId(42)).sync().getResult(0).getPayloadMap();
		assertEquals("There should only be one remaining prop", 1, after.size());
		assertFalse("The prop should have been deleted", after.containsKey(extraKey));
		assertTrue("The prop should be still there", after.containsKey("color"));
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
	public void testScrollPointsOffset() throws Exception {
		// First request
		ScrollResponse scrollResponse1 = client.scrollPoint(TEST_COLLECTION_NAME, null, 1, null, null, null).sync();
		List<RetrievedPoint> list = scrollResponse1.getResultList();
		assertEquals(4, list.size());
		assertNotNull(scrollResponse1.getNextPageOffset());

		// Second request
		ScrollResponse scrollResponse2 = client.scrollPoint(TEST_COLLECTION_NAME, scrollResponse1.getNextPageOffset(), 1, null, null, null).sync();
		List<RetrievedPoint> list2 = scrollResponse2.getResultList();
		assertEquals(4, list2.size());
		assertNotNull(scrollResponse2.getNextPageOffset());
	}

	@Test
	@Override
	public void testSearchPoints() throws Exception {
		float[] vector = new float[] { 2.41f, 0.9f, 0.81f, 2.45f };
		SearchResponse response = client.searchPoints(TEST_COLLECTION_NAME, TEST_VECTOR_NAME, vector, 2, 100f).sync();
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
			.setVectorName(TEST_VECTOR_NAME)
			.setCollectionName(TEST_COLLECTION_NAME);

		searches.add(request.build());

		SearchBatchResponse responses = client.searchBatch(TEST_COLLECTION_NAME, searches).sync();
		assertEquals(1, responses.getResultList().size());
	}

	@Test
	@Override
	public void testRecommendPoints() throws Exception {
		RecommendResponse result = client.recommendPoints(TEST_COLLECTION_NAME, pointIds(42L), 2, TEST_VECTOR_NAME).sync();
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
			.setUsing(TEST_VECTOR_NAME)
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
			NamedVectors vectors = ModelHelper.namedVector(TEST_VECTOR_NAME, new float[] { 0.43f + i, 0.1f, 0.61f, 1.45f });
			PointStruct point = PointStruct.newBuilder()
				.putPayload(TEST_VECTOR_NAME, ModelHelper.value("blue"))
				.setId(ModelHelper.pointId(82L + i))
				.setVectors(Vectors.newBuilder().setVectors(vectors).build())
				.build();
			assertEquals(UpdateStatus.Completed, client.upsertPoint(TEST_COLLECTION_NAME, point, true).sync().getResult().getStatus());
		}
		assertPointCount(14, TEST_COLLECTION_NAME);
	}

	@Test
	@Override
	public void testUpdateVectors() throws Exception {
		GetResponse response = client.getPoint(TEST_COLLECTION_NAME, true, true, pointId(42L)).sync();
		assertFalse(response.getResult(0).getVectors().getVectors().containsVectors(TEST_VECTOR_NAME_2));

		UpdatePointVectors request = UpdatePointVectors
			.newBuilder()
			.setCollectionName(TEST_COLLECTION_NAME)
			.addPoints(PointVectors.newBuilder()
				.setId(ModelHelper.pointId(42L))
				.setVectors(Vectors.newBuilder().setVectors(ModelHelper.namedVector(TEST_VECTOR_NAME_2, new float[] { 0.4f, 0.1f, 0.2f, 0.3f })))
				.build())
			.build();
		client.updateVectors(request).sync();

		GetResponse response2 = client.getPoint(TEST_COLLECTION_NAME, true, true, pointId(42L)).sync();
		assertTrue(response2.getResult(0).getVectors().getVectors().containsVectors(TEST_VECTOR_NAME_2));
	}

	@Test
	@Override
	public void testDeleteVectors() throws Exception {
		UpdatePointVectors request = UpdatePointVectors
			.newBuilder()
			.setCollectionName(TEST_COLLECTION_NAME)
			.addPoints(PointVectors.newBuilder()
				.setId(ModelHelper.pointId(42L))
				.setVectors(Vectors.newBuilder().setVectors(ModelHelper.namedVector(TEST_VECTOR_NAME_2, new float[] { 0.4f, 0.1f, 0.2f, 0.3f })))
				.build())
			.build();
		client.updateVectors(request).sync();

		DeletePointVectors request2 = DeletePointVectors.newBuilder()
			.setCollectionName(TEST_COLLECTION_NAME)
			.setWait(true)
			.setPointsSelector(selectByIds(42L))
			.setVectors(VectorsSelector.newBuilder().addNames(TEST_VECTOR_NAME_2))
			.build();
		client.deleteVectors(request2).sync();

		GetResponse response = client.getPoint(TEST_COLLECTION_NAME, true, true, pointId(42L)).sync();
		assertFalse(response.getResult(0).getVectors().getVectors().containsVectors(TEST_VECTOR_NAME_2));

	}

	@Test
	@Override
	public void testSearchGroupPoints() throws Exception {
		SearchPointGroups request = SearchPointGroups.newBuilder()
			.setCollectionName(TEST_COLLECTION_NAME)
			.setGroupBy(TEST_VECTOR_NAME)
			.setGroupSize(10)
			.setWithVectors(withVector())
			.setWithPayload(withPayload())
			.setLimit(10)
			.setVectorName(TEST_VECTOR_NAME)
			.addAllVector(vectorList(0.1f, 0.2f, 0.3f, 0.4f))
			.build();
		client.searchGroupPoints(request).sync();
	}

	@Test
	@Override
	public void testRecommendGroupPoints() throws Exception {
		RecommendPointGroups request = RecommendPointGroups.newBuilder()
			.setCollectionName(TEST_COLLECTION_NAME)
			.setGroupBy("color")
			.setGroupSize(10)
			.setWithVectors(withVector())
			.setWithPayload(withPayload())
			.addAllPositive(pointIds(42L))
			.setUsing(TEST_VECTOR_NAME)
			.setLimit(10)
			.build();
		client.recommendGroupPoints(request).sync();

	}

}
