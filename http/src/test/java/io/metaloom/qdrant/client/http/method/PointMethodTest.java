package io.metaloom.qdrant.client.http.method;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

import io.metaloom.qdrant.client.http.AbstractClientTest;
import io.metaloom.qdrant.client.http.impl.HttpErrorException;
import io.metaloom.qdrant.client.http.model.collection.CollectionCreateRequest;
import io.metaloom.qdrant.client.http.model.collection.config.Distance;
import io.metaloom.qdrant.client.http.model.point.PointCountRequest;
import io.metaloom.qdrant.client.http.model.point.PointCountResponse;
import io.metaloom.qdrant.client.http.model.point.PointDeletePayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointOverwritePayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointSetPayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointStruct;
import io.metaloom.qdrant.client.http.model.point.PointsClearPayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointsDeleteRequest;
import io.metaloom.qdrant.client.http.model.point.PointsGetRequest;
import io.metaloom.qdrant.client.http.model.point.PointsListUpsertRequest;
import io.metaloom.qdrant.client.http.model.point.PointsRecommendBatchRequest;
import io.metaloom.qdrant.client.http.model.point.PointsRecommendRequest;
import io.metaloom.qdrant.client.http.model.point.PointsScrollRequest;
import io.metaloom.qdrant.client.http.model.point.PointsSearchBatchRequest;
import io.metaloom.qdrant.client.http.model.point.PointsSearchRequest;
import io.metaloom.qdrant.client.http.model.point.PointsUpsertRequest;
import io.metaloom.qdrant.client.http.model.point.Record;
import io.metaloom.qdrant.json.Json;

public class PointMethodTest extends AbstractClientTest {

	@Before
	public void setupTestData() throws Exception {
		CollectionCreateRequest collection = new CollectionCreateRequest();
		collection.setVectors(4, Distance.EUCLID);
		invoke(client.createCollection(TEST_COLLECTION_NAME, collection));

		PointStruct p1 = new PointStruct();
		PointStruct p4 = new PointStruct();
		PointStruct p2 = new PointStruct();
		PointStruct p3 = new PointStruct();
		p1.setId(1);
		p1.setVector(0.42f, 0.33f, 42.15f, 68.72f);
		p1.setPayload("{\"name\": \"first\"}");
		p2.setId(2);
		p2.setVector(0.12f, 0.23f, 12.46f, 47.17f);
		p3.setId(3);
		p3.setVector(0.76f, 0.43f, 63.45f, 22.10f);
		p4.setId(4);
		p4.setVector(0.17f, 0.36f, 72.39f, 0.0f);
		JsonNode json = Json.toJson("{ \"color\": \"red\"}");
		p4.setPayload(json);
		PointsListUpsertRequest pointsRequest = new PointsListUpsertRequest();
		pointsRequest.setPoints(p1, p2, p3, p4);
		invoke(client.upsertPoints(TEST_COLLECTION_NAME, pointsRequest, false));
	}

	@Test
	public void testGetPoint() throws HttpErrorException {
		Record point = invoke(client.getPoint(TEST_COLLECTION_NAME, "1")).getResult();
		assertEquals("first", point.getPayload().getJson().get("name").asText());
	}

	@Test
	public void testGetPoints() throws HttpErrorException {
		PointsGetRequest request = new PointsGetRequest();
		invoke(client.getPoints(TEST_COLLECTION_NAME, request));
	}

	@Test
	public void testUpsertPoints() throws HttpErrorException {
		// TODO test both variants
		PointsUpsertRequest request = new PointsListUpsertRequest();
		invoke(client.upsertPoints(TEST_COLLECTION_NAME, request, true));
	}

	@Test
	public void testDeletePoints() throws HttpErrorException {
		PointsDeleteRequest request = new PointsDeleteRequest();
		invoke(client.deletePoints(TEST_COLLECTION_NAME, request, true));
	}

	@Test
	public void testSetPointPayload() throws HttpErrorException {
		PointSetPayloadRequest request = new PointSetPayloadRequest();
		invoke(client.setPointPayload(TEST_COLLECTION_NAME, request, true));
	}

	@Test
	public void testOverwritePointPayload() throws HttpErrorException {
		PointOverwritePayloadRequest request = new PointOverwritePayloadRequest();
		invoke(client.overwritePayload(TEST_COLLECTION_NAME, request, true));
	}

	@Test
	public void testDeletePointPayload() throws HttpErrorException {
		PointDeletePayloadRequest request = new PointDeletePayloadRequest();
		invoke(client.deletePayload(TEST_COLLECTION_NAME, request, true));
	}

	@Test
	public void testClearPointPayload() throws HttpErrorException {
		PointsClearPayloadRequest request = new PointsClearPayloadRequest();
		invoke(client.clearPayload(TEST_COLLECTION_NAME, request, true));
	}

	@Test
	public void testScrollPoints() throws HttpErrorException {
		PointsScrollRequest request = new PointsScrollRequest();
		invoke(client.scrollPoints(TEST_COLLECTION_NAME, request));
	}

	@Test
	public void testSearchPoints() throws HttpErrorException {
		PointsSearchRequest request = new PointsSearchRequest();
		invoke(client.searchPoints(TEST_COLLECTION_NAME, request));
	}

	@Test
	public void testSearchBatchPoints() throws HttpErrorException {
		PointsSearchBatchRequest request = new PointsSearchBatchRequest();
		invoke(client.searchBatchPoints(TEST_COLLECTION_NAME, request));
	}

	@Test
	public void testRecommendPoints() throws HttpErrorException {
		PointsRecommendRequest request = new PointsRecommendRequest();
		invoke(client.recommendPoints(TEST_COLLECTION_NAME, request));
	}

	@Test
	public void testRecommendBatchPoints() throws HttpErrorException {
		PointsRecommendBatchRequest request = new PointsRecommendBatchRequest();
		invoke(client.recommendBatchPoints(TEST_COLLECTION_NAME, request));
	}

	@Test
	public void testCountPoints() throws HttpErrorException {
		PointCountRequest request = new PointCountRequest();
		request.setExact(true);

		PointCountResponse response = invoke(client.countPoints(TEST_COLLECTION_NAME, request));
		assertEquals(4, response.getResult().getCount());
	}
}
