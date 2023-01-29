package io.metaloom.qdrant.client.http.method;

import static io.metaloom.qdrant.client.http.test.QDrantHttpClientAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;

import io.metaloom.qdrant.client.http.AbstractClientTest;
import io.metaloom.qdrant.client.http.impl.HttpErrorException;
import io.metaloom.qdrant.client.http.model.collection.CollectionCreateRequest;
import io.metaloom.qdrant.client.http.model.collection.config.Distance;
import io.metaloom.qdrant.client.http.model.collection.filter.Filter;
import io.metaloom.qdrant.client.http.model.collection.filter.condition.HasIdCondition;
import io.metaloom.qdrant.client.http.model.point.NamedVector;
import io.metaloom.qdrant.client.http.model.point.Payload;
import io.metaloom.qdrant.client.http.model.point.PointCountRequest;
import io.metaloom.qdrant.client.http.model.point.PointCountResponse;
import io.metaloom.qdrant.client.http.model.point.PointDeletePayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointOverwritePayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointSetPayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointStruct;
import io.metaloom.qdrant.client.http.model.point.PointsBatch;
import io.metaloom.qdrant.client.http.model.point.PointsBatchUpsertRequest;
import io.metaloom.qdrant.client.http.model.point.PointsClearPayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointsFilterDeleteRequest;
import io.metaloom.qdrant.client.http.model.point.PointsGetRequest;
import io.metaloom.qdrant.client.http.model.point.PointsGetResponse;
import io.metaloom.qdrant.client.http.model.point.PointsListDeleteRequest;
import io.metaloom.qdrant.client.http.model.point.PointsListUpsertRequest;
import io.metaloom.qdrant.client.http.model.point.PointsRecommendBatchRequest;
import io.metaloom.qdrant.client.http.model.point.PointsRecommendRequest;
import io.metaloom.qdrant.client.http.model.point.PointsScrollRequest;
import io.metaloom.qdrant.client.http.model.point.PointsScrollResponse;
import io.metaloom.qdrant.client.http.model.point.PointsSearchBatchRequest;
import io.metaloom.qdrant.client.http.model.point.PointsSearchRequest;
import io.metaloom.qdrant.client.http.model.point.Record;
import io.metaloom.qdrant.client.http.model.point.Vector;
import io.metaloom.qdrant.client.json.Json;

public class PointMethodTest extends AbstractClientTest {

	public static String VECTOR_NAME = "test-points";
	public static float[] VECTOR_1 = { 0.42f, 0.33f, 42.15f, 68.72f };
	public static float[] NEAR_VECTOR_1 = { 0.41f, 0.32f, 42.11f, 68.71f };
	public static float[] VECTOR_2 = { 0.12f, 0.23f, 12.46f, 47.17f };
	public static float[] VECTOR_3 = { 0.76f, 0.43f, 63.45f, 22.10f };
	public static float[] VECTOR_4 = { 0.17f, 0.36f, 72.39f, 0.0f };

	@Before
	public void setupTestData() throws Exception {
		CollectionCreateRequest collection = new CollectionCreateRequest();
		collection.setVectors(VECTOR_NAME, 4, Distance.EUCLID);
		invoke(client.createCollection(TEST_COLLECTION_NAME, collection));

		PointStruct p1 = PointStruct.of(VECTOR_NAME, VECTOR_1).setId(1);
		p1.setPayload("{\"name\": \"first\"}");

		PointStruct p2 = PointStruct.of(VECTOR_NAME, VECTOR_2).setId(2);
		PointStruct p3 = PointStruct.of(VECTOR_NAME, VECTOR_3).setId(3);
		PointStruct p4 = PointStruct.of(VECTOR_NAME, VECTOR_4).setId(4);

		JsonNode json = Json.toJson("{ \"color\": \"red\"}");
		p4.setPayload(json);
		PointsListUpsertRequest pointsRequest = new PointsListUpsertRequest();
		pointsRequest.setPoints(p1, p2, p3, p4);
		invoke(client.upsertPoints(TEST_COLLECTION_NAME, pointsRequest, false));

		assertThat(client).hasPoints(TEST_COLLECTION_NAME, 4);
	}

	@Test
	public void testGetPoint() throws HttpErrorException {
		Record point = invoke(client.getPoint(TEST_COLLECTION_NAME, "1")).getResult();
		assertEquals("first", point.getPayload().getJson().get("name").asText());
	}

	@Test
	public void testGetPoints() throws HttpErrorException {
		PointsGetRequest request = new PointsGetRequest();
		request.setWithVector(true);
		request.setWithPayload(true);
		request.setIds(1L);

		PointsGetResponse resp = invoke(client.getPoints(TEST_COLLECTION_NAME, request));
		assertNotNull(resp.getResult().get(0).getPayload());
		assertNotNull(resp.getResult().get(0).getVector());
	}

	@Test
	public void testUpsertPoints() throws HttpErrorException, JacksonException {
		PointsListUpsertRequest listRequest = new PointsListUpsertRequest();
		listRequest.setPoints(PointStruct.of(42.51f, 51f, 0.14f, 516.1f));
		invoke(client.upsertPoints(TEST_COLLECTION_NAME, listRequest, true));

		PointsBatchUpsertRequest batchRequest = new PointsBatchUpsertRequest();
		PointsBatch batch = new PointsBatch();
		batch.setIds(4L);
		batch.setPayloads(Payload.of("{\"name\": \"fourth\"}"));
		batch.setVectors(Vector.of(42f, 66f, 1f, 0.42f));
		batchRequest.setBatch(batch);
		invoke(client.upsertPoints(TEST_COLLECTION_NAME, batchRequest, true));

		assertThat(client).hasPoints(TEST_COLLECTION_NAME, 5);
	}

	@Test
	public void testDeletePoints() throws HttpErrorException {
		assertThat(client).hasPoints(TEST_COLLECTION_NAME, 4);

		// Delete via list of ids
		PointsListDeleteRequest listRequest = new PointsListDeleteRequest();
		listRequest.setPoints(1L, 2L);
		invoke(client.deletePoints(TEST_COLLECTION_NAME, listRequest, true));
		assertThat(client).hasPoints(TEST_COLLECTION_NAME, 2);

		// Now delete via filter
		PointsFilterDeleteRequest filterRequest = new PointsFilterDeleteRequest();
		filterRequest.setFilter(new Filter().setMust(HasIdCondition.of(3L)));
		invoke(client.deletePoints(TEST_COLLECTION_NAME, filterRequest, true));
		assertThat(client).hasPoints(TEST_COLLECTION_NAME, 1);
	}

	@Test
	public void testSetPointPayload() throws Exception {
		// 1. Set the payload
		PointSetPayloadRequest request = new PointSetPayloadRequest();
		request.setPoints(1L);
		request.setPayload(Payload.of(json("newKey", "set-payload")));
		invoke(client.setPointPayload(TEST_COLLECTION_NAME, request, true));

		// 2. Load one point with payload
		PointsGetRequest loadRequest = new PointsGetRequest();
		loadRequest.setWithPayload(true);
		loadRequest.setIds(1L);
		PointsGetResponse resp = invoke(client.getPoints(TEST_COLLECTION_NAME, loadRequest));
		assertEquals("We expect the point to have a added payload property.", "set-payload",
			resp.first().getPayload().text("newKey"));
		assertEquals("We expect the original payload property to be not modified.", "first",
			resp.first().getPayload().text("name"));

	}

	@Test
	public void testOverwritePointPayload() throws Exception {
		// 1. Overwrite the payload
		PointOverwritePayloadRequest request = new PointOverwritePayloadRequest();
		request.setPoints(1L);
		request.setPayload(Payload.of(json("newKey", "set-payload")));
		invoke(client.overwritePayload(TEST_COLLECTION_NAME, request, true));

		// 2. Load one point with payload
		PointsGetRequest loadRequest = new PointsGetRequest();
		loadRequest.setWithPayload(true);
		loadRequest.setIds(1L);
		PointsGetResponse resp = invoke(client.getPoints(TEST_COLLECTION_NAME, loadRequest));
		assertEquals("We expect the point to have a added payload property.", "set-payload",
			resp.first().getPayload().text("newKey"));
		assertNull("We expect the original payload property to be removed.", resp.first().getPayload().text("name"));

	}

	@Test
	public void testDeletePointPayload() throws Exception {
		// 1. Add an extra property
		PointSetPayloadRequest request = new PointSetPayloadRequest();
		request.setPoints(1L);
		request.setPayload(Payload.of(json("newKey", "set-payload")));
		invoke(client.setPointPayload(TEST_COLLECTION_NAME, request, true));

		// 2. Delete the name property from payloads
		PointDeletePayloadRequest request2 = new PointDeletePayloadRequest();
		request2.setPoints(1L);
		request2.setKeys("name");
		invoke(client.deletePayload(TEST_COLLECTION_NAME, request2, true));

		// 3. Load one point with payload
		PointsGetRequest loadRequest = new PointsGetRequest();
		loadRequest.setWithPayload(true);
		loadRequest.setIds(1L);
		PointsGetResponse resp = invoke(client.getPoints(TEST_COLLECTION_NAME, loadRequest));
		assertNull("We expect the original payload property to be removed.", resp.first().getPayload().text("name"));
		assertNotNull("We expect the extra payload property to be untouched.", resp.first().getPayload().text("newKey"));
	}

	@Test
	public void testClearPointPayload() throws Exception {
		// 1. Clear the payload from the point
		PointsClearPayloadRequest request = new PointsClearPayloadRequest();
		request.setPoints(1L);
		invoke(client.clearPayload(TEST_COLLECTION_NAME, request, true));

		// 2. Load one point with payload
		PointsGetRequest loadRequest = new PointsGetRequest();
		loadRequest.setWithPayload(true);
		loadRequest.setIds(1L);
		PointsGetResponse resp = invoke(client.getPoints(TEST_COLLECTION_NAME, loadRequest));
		assertNull("We expect the original payload property to be removed.", resp.first().getPayload().text("name"));

	}

	@Test
	public void testScrollPoints() throws HttpErrorException {
		PointsScrollRequest request = new PointsScrollRequest();
		// Set page size to 1
		request.setLimit(1);
		PointsScrollResponse resp = invoke(client.scrollPoints(TEST_COLLECTION_NAME, request));
		assertEquals(1, resp.getResult().getPoints().size());
		Long nextOffset = resp.getResult().getNextPageOffset();
		assertEquals(2L, nextOffset.longValue());
	}

	@Test
	public void testSearchPoints() throws HttpErrorException {
		PointsSearchRequest request = new PointsSearchRequest();
		request.setVector(NamedVector.of("default", NEAR_VECTOR_1));
		request.setLimit(10);
		request.setWithPayload(true);
		request.setWithVector(true);
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
