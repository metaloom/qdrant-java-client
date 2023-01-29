package io.metaloom.qdrant.client.http.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.metaloom.qdrant.client.http.model.collection.filter.condition.FieldCondition;
import io.metaloom.qdrant.client.http.model.collection.filter.condition.HasIdCondition;
import io.metaloom.qdrant.client.http.model.collection.filter.condition.IsEmptyCondition;
import io.metaloom.qdrant.client.http.model.point.PointCountRequest;
import io.metaloom.qdrant.client.http.model.point.PointCountResponse;
import io.metaloom.qdrant.client.http.model.point.PointDeletePayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointGetResponse;
import io.metaloom.qdrant.client.http.model.point.PointSetPayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointsBatchUpsertRequest;
import io.metaloom.qdrant.client.http.model.point.PointsGetResponse;
import io.metaloom.qdrant.client.http.model.point.PointsListUpsertRequest;
import io.metaloom.qdrant.client.http.model.point.PointsSearchRequest;
import io.metaloom.qdrant.client.http.model.point.UpdateResultResponse;
import io.metaloom.qdrant.client.http.model.point.UpdateStatus;
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
	}

	@Test
	public void testPointsUpsertBatchRequestModel() {
		PointsBatchUpsertRequest req = load("point/points-batch-upsert-request", PointsBatchUpsertRequest.class);
		assertEquals(2L, req.getBatch().getIds().get(1).longValue());
	}

	@Test
	public void testPointsUpsertListRequestModel() {
		PointsListUpsertRequest req = load("point/points-list-upsert-request", PointsListUpsertRequest.class);
		assertEquals(3L, req.getPoints().get(2).getId());
	}

	@Test
	public void testNamedPointsUpsertListRequestModel() {
		PointsListUpsertRequest req = load("point/named-points-list-upsert-request", PointsListUpsertRequest.class);
		assertEquals(2L, req.getPoints().get(1).getId());
		String json = Json.parse(req);
		System.out.println(json);
	}

	@Test
	public void testPointGetResponseModel() {
		PointGetResponse response = load("point/point-get-response", PointGetResponse.class);
		assertEquals(42, response.getResult().getId().longValue());
		assertTrue(response.getResult().getPayload().getJson().isEmpty());
		assertEquals(1, response.getResult().getVector().size());
	}

	@Test
	public void testPointsGetResponseModel() {
		PointsGetResponse response = load("point/points-get-response", PointsGetResponse.class);
		assertEquals(42, response.getResult().get(0).getId().longValue());
	}

	@Test
	public void testPointsSearchRequestModel() {
		PointsSearchRequest request = load("point/points-search-request", PointsSearchRequest.class);
		assertEquals(42, request.getParams().getHnswBeamSearchSize().longValue());
		assertEquals(42.42f, request.getVector().getVector().get(1), 0f);
	}
}
