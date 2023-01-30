package io.metaloom.qdrant.client.http.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import io.metaloom.qdrant.client.http.model.collection.filter.condition.FieldCondition;
import io.metaloom.qdrant.client.http.model.collection.filter.condition.HasIdCondition;
import io.metaloom.qdrant.client.http.model.collection.filter.condition.IsEmptyCondition;
import io.metaloom.qdrant.client.http.model.point.BatchVectorDataMap;
import io.metaloom.qdrant.client.http.model.point.BatchVectorDataPlain;
import io.metaloom.qdrant.client.http.model.point.PointCountRequest;
import io.metaloom.qdrant.client.http.model.point.PointCountResponse;
import io.metaloom.qdrant.client.http.model.point.PointDeletePayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointGetResponse;
import io.metaloom.qdrant.client.http.model.point.PointSetPayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointStruct;
import io.metaloom.qdrant.client.http.model.point.PointsBatchUpsertRequest;
import io.metaloom.qdrant.client.http.model.point.PointsGetResponse;
import io.metaloom.qdrant.client.http.model.point.PointsListUpsertRequest;
import io.metaloom.qdrant.client.http.model.point.PointsRecommendRequest;
import io.metaloom.qdrant.client.http.model.point.PointsSearchRequest;
import io.metaloom.qdrant.client.http.model.point.UpdateResultResponse;
import io.metaloom.qdrant.client.http.model.point.UpdateStatus;
import io.metaloom.qdrant.client.http.model.point.VectorDataMap;
import io.metaloom.qdrant.client.http.model.point.VectorDataPlain;
import io.metaloom.qdrant.client.json.Json;

public class PointModelTest extends AbstractModelTest {

	@Test
	public void testPointCountResponseModel() {
		PointCountResponse response = load("point/point-count-response", PointCountResponse.class);
		assertEquals(42, response.getResult().getCount());
	}

	@Test
	public void testPointCountRequestModel() {
		PointCountRequest request = load("point/point-count-request", PointCountRequest.class);
		FieldCondition shouldCondition = (FieldCondition) request.getFilter().getShould().get(0);
		assertEquals("string", shouldCondition.getKey());

		// Check Has Id
		assertEquals(HasIdCondition.class, request.getFilter().getMust().get(1).getClass());
		HasIdCondition cond = (HasIdCondition) request.getFilter().getMust().get(1);
		assertEquals(6, cond.getIds().size());

		// Check is Empty
		IsEmptyCondition cond2 = (IsEmptyCondition) request.getFilter().getMust().get(2);
		assertEquals("reports", cond2.getIsEmpty().getKey());
	}

	@Test
	public void testPointSetPayloadRequestModel() {
		PointSetPayloadRequest request = load("point/point-set-payload-request", PointSetPayloadRequest.class);
		assertEquals("jacket", request.getPayload().getJson().get("name").asText());
	}

	@Test
	public void testUpdateResultResponseModel() {
		UpdateResultResponse response = load("update-result-response", UpdateResultResponse.class);
		assertEquals(UpdateStatus.ACKNOWLEDGED, response.getResult().getStatus());
	}

	@Test
	public void testPointDeletePayloadRequestModel() {
		PointDeletePayloadRequest req = load("point/point-delete-payload-request", PointDeletePayloadRequest.class);
		assertNotNull(req);
	}

	@Test
	public void testPointsUpsertListRequestModel() {
		PointsListUpsertRequest req = load("point/points-list-upsert-request", PointsListUpsertRequest.class);
		assertId(3, req.getPoints().get(2).getId());

		PointsListUpsertRequest req2 = new PointsListUpsertRequest();
		req2.setPoints(PointStruct.of(0.42f));
		Json.parse(req2);
	}

	@Test
	public void testNamedPointsUpsertListRequestModel() {
		PointsListUpsertRequest req = load("point/named-points-list-upsert-request", PointsListUpsertRequest.class);
		assertId(2, req.getPoints().get(1).getId());
		Json.parse(req);
	}

	@Test
	public void testPointsUpsertBatchRequestModel() {
		PointsBatchUpsertRequest req = load("point/points-batch-upsert-request", PointsBatchUpsertRequest.class);
		assertId(2, req.getBatch().getIds().get(1));
	}

	@Test
	public void testBatchVectorDataMap() {
		BatchVectorDataMap vectorDataMap = new BatchVectorDataMap();
		vectorDataMap.put("test", Arrays.asList(Arrays.asList(1.0f, 2.0f)));
		assertEquals("{\"test\":[[1.0,2.0]]}", Json.parseCompact(vectorDataMap));
	}

	@Test
	public void testBatchVectorPlain() {
		BatchVectorDataPlain vectorDataPlain = new BatchVectorDataPlain();
		vectorDataPlain.add(Arrays.asList(1.0f, 2.0f));
		assertEquals("[[1.0,2.0]]", Json.parseCompact(vectorDataPlain));
	}

	@Test
	public void testPointsUpsertBatchRequestWithNamedVector() {
		PointsBatchUpsertRequest req = load("point/named-points-batch-upsert-request", PointsBatchUpsertRequest.class);
		// req.getBatch().getVectors()
		String json = Json.parse(req);
	}

	@Test
	public void testPointGetResponseModel() {
		PointGetResponse response = load("point/point-get-response", PointGetResponse.class);
		assertId(42, response.getResult().getId());
		assertTrue(response.getResult().getPayload().getJson().isEmpty());
		VectorDataPlain vectorData = (VectorDataPlain) response.getResult().getVector();
		assertEquals(1, vectorData.getVector().size());
	}

	@Test
	public void testPointGetResponseModel2() {
		PointGetResponse response = load("point/point-get-response-2", PointGetResponse.class);
		assertId(42, response.getResult().getId());
		assertTrue("The payload should list a name property.", response.getResult().getPayload().getJson().has("name"));
		VectorDataMap vectorData = (VectorDataMap) response.getResult().getVector();

		assertEquals("There should be four values for the listed vector", 4, vectorData.get("test-points").size());
	}

	@Test
	public void testPointsGetResponseModel() {
		PointsGetResponse response = load("point/points-get-response", PointsGetResponse.class);
		assertId(42, response.getResult().get(0).getId());
	}

	@Test
	public void testPointsSearchRequestModel() {
		PointsSearchRequest request = load("point/points-search-request", PointsSearchRequest.class);
		assertEquals(42, request.getParams().getHnswBeamSearchSize().longValue());
		assertEquals(42.42f, request.getVector().getVector().get(1), 0f);
	}

	@Test
	public void testPointsRecommendRequestModel() {
		load("point/points-recommend-request", PointsRecommendRequest.class);
	}

	@Test
	public void testPointsRecommendRequestModel2() {
		load("point/points-recommend-request-2", PointsRecommendRequest.class);
	}

}
