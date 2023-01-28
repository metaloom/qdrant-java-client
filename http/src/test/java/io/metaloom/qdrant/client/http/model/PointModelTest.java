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
import io.metaloom.qdrant.client.http.model.point.PointsGetResponse;
import io.metaloom.qdrant.client.http.model.point.PointsListUpsertRequest;
import io.metaloom.qdrant.client.http.model.point.PointsSearchRequest;
import io.metaloom.qdrant.client.http.model.point.UpdateResultResponse;
import io.metaloom.qdrant.client.http.model.point.UpdateStatus;

public class PointModelTest extends AbstractModelTest {

	@Test
	public void testPointCountResponseModel() {
		PointCountResponse response = load("point-count-response", PointCountResponse.class);
		assertEquals(42, response.getResult().getCount());
	}

	@Test
	public void testPointCountRequestModel() {
		PointCountRequest request = load("point-count-request", PointCountRequest.class);
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
		PointSetPayloadRequest request = load("point-set-payload-request", PointSetPayloadRequest.class);
		assertEquals("jacket", request.getPayload().getJson().get("name").asText());
	}

	@Test
	public void testUpdateResultResponseModel() {
		UpdateResultResponse response = load("update-result-response", UpdateResultResponse.class);
		assertEquals(UpdateStatus.ACKNOWLEDGED, response.getResult().getStatus());
	}

	@Test
	public void testPointDeletePayloadRequestModel() {
		PointDeletePayloadRequest req = load("point-delete-payload-request", PointDeletePayloadRequest.class);
	}

	@Test
	public void testPointsUpsertRequestModelPointsBatch() {
		PointsListUpsertRequest req = load("points-upsert-request", PointsListUpsertRequest.class);
		assertEquals(42L, req.getPoints().get(0).getId());
	}

	@Test
	public void testPointsUpsertRequestModelListBatch() {
		PointsListUpsertRequest req = load("points-upsert-list-batch-request", PointsListUpsertRequest.class);
		assertEquals(42, req.getPoints().get(0).getId());
	}

	@Test
	public void testPointGetResponseModel() {
		PointGetResponse response = load("point-get-response", PointGetResponse.class);
		assertEquals(42, response.getResult().getId().longValue());
		assertTrue(response.getResult().getPayload().getJson().isEmpty());
		assertEquals(1, response.getResult().getVector().size());
	}

	@Test
	public void testPointsGetResponseModel() {
		PointsGetResponse response = load("points-get-response", PointsGetResponse.class);
		assertEquals(42, response.getResult().get(0).getId().longValue());
	}

	@Test
	public void testPointsSearchRequestModel() {
		PointsSearchRequest request = load("points-search-request", PointsSearchRequest.class);
		assertEquals(42, request.getParams().getHnswBeamSearchSize().longValue());
		assertEquals(42.42f, request.getVector().getVector().get(1), 0f);
	}
}
