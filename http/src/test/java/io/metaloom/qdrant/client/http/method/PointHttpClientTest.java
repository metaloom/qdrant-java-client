package io.metaloom.qdrant.client.http.method;

import static io.metaloom.qdrant.client.http.test.QDrantHttpClientAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;

import io.metaloom.qdrant.client.http.AbstractHTTPClientTest;
import io.metaloom.qdrant.client.http.impl.HttpErrorException;
import io.metaloom.qdrant.client.http.model.collection.CollectionCreateRequest;
import io.metaloom.qdrant.client.http.model.collection.config.Distance;
import io.metaloom.qdrant.client.http.model.collection.filter.Filter;
import io.metaloom.qdrant.client.http.model.collection.filter.condition.FieldCondition;
import io.metaloom.qdrant.client.http.model.collection.filter.condition.HasIdCondition;
import io.metaloom.qdrant.client.http.model.collection.filter.match.MatchText;
import io.metaloom.qdrant.client.http.model.collection.schema.CollectionCreateIndexFieldRequest;
import io.metaloom.qdrant.client.http.model.collection.schema.PayloadIndexSchemaType;
import io.metaloom.qdrant.client.http.model.point.BatchVectorDataMap;
import io.metaloom.qdrant.client.http.model.point.BatchVectorDataPlain;
import io.metaloom.qdrant.client.http.model.point.NamedVector;
import io.metaloom.qdrant.client.http.model.point.Payload;
import io.metaloom.qdrant.client.http.model.point.PointCountRequest;
import io.metaloom.qdrant.client.http.model.point.PointCountResponse;
import io.metaloom.qdrant.client.http.model.point.PointDeletePayloadRequest;
import io.metaloom.qdrant.client.http.model.point.PointId;
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
import io.metaloom.qdrant.client.http.model.point.PointsRecommendBatchResponse;
import io.metaloom.qdrant.client.http.model.point.PointsRecommendRequest;
import io.metaloom.qdrant.client.http.model.point.PointsRecommendResponse;
import io.metaloom.qdrant.client.http.model.point.PointsScrollRequest;
import io.metaloom.qdrant.client.http.model.point.PointsScrollResponse;
import io.metaloom.qdrant.client.http.model.point.PointsSearchBatchRequest;
import io.metaloom.qdrant.client.http.model.point.PointsSearchBatchResponse;
import io.metaloom.qdrant.client.http.model.point.PointsSearchRequest;
import io.metaloom.qdrant.client.http.model.point.PointsSearchResponse;
import io.metaloom.qdrant.client.http.model.point.Record;
import io.metaloom.qdrant.client.json.Json;
import io.metaloom.qdrant.client.testcases.PointClientTestcases;

public class PointHttpClientTest extends AbstractHTTPClientTest implements PointClientTestcases {

	@Before
	public void setupTestData() throws Exception {
		CollectionCreateRequest collection = new CollectionCreateRequest();
		collection.setVectors(VECTOR_NAME, 4, Distance.EUCLID);
		invoke(client.createCollection(TEST_COLLECTION_NAME, collection));

		PointStruct p1 = PointStruct.of(VECTOR_NAME, VECTOR_1).setId(1);
		p1.setPayload("{\"name\": \"first\"}");

		PointStruct p2 = PointStruct.of(VECTOR_NAME, VECTOR_2).setId(2);
		PointStruct p3 = PointStruct.of(VECTOR_NAME, VECTOR_3).setId(3);
		p3.setPayload("{\"name\": \"third\"}");
		PointStruct p4 = PointStruct.of(VECTOR_NAME, VECTOR_4).setId(4);

		JsonNode json = Json.toJson("{ \"color\": \"red\"}");
		p4.setPayload(json);
		PointsListUpsertRequest pointsRequest = new PointsListUpsertRequest();
		pointsRequest.setPoints(p1, p2, p3, p4);
		invoke(client.upsertPoints(TEST_COLLECTION_NAME, pointsRequest, true));
		assertThat(client).hasPoints(TEST_COLLECTION_NAME, 4);
	}

	@Test
	@Override
	public void testGetPoint() throws HttpErrorException {
		Record point = invoke(client.getPoint(TEST_COLLECTION_NAME, 1L)).getResult();
		assertEquals("first", point.getPayload().getJson().get("name").asText());
	}

	@Test
	@Override
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
	@Override
	public void testUpsertPointViaList() throws HttpErrorException {
		PointsListUpsertRequest listRequest = new PointsListUpsertRequest();
		listRequest.setPoints(PointStruct.of(VECTOR_NAME, 42.51f, 51f, 0.14f, 516.1f).setId(42L));
		invoke(client.upsertPoints(TEST_COLLECTION_NAME, listRequest, true));
	}

	@Test
	@Override
	public void testUpsertPointsViaListBatch() throws HttpErrorException, JacksonException {
		// Create unnamed collection
		String collectionName = TEST_COLLECTION_NAME + "-unnamed";
		CollectionCreateRequest collection = new CollectionCreateRequest();
		collection.setVectors(4, Distance.EUCLID);
		invoke(client.createCollection(collectionName, collection));

		// Now invoke the request
		PointsBatchUpsertRequest batchRequest = new PointsBatchUpsertRequest();
		PointsBatch batch = new PointsBatch();
		batch.setIds(4L);
		batch.setPayloads(Payload.of("{\"name\": \"fourth\"}"));
		BatchVectorDataPlain vectorDataPlain = new BatchVectorDataPlain();
		vectorDataPlain.add(Arrays.asList(42f, 66f, 1f, 0.42f));
		batch.setVectors(vectorDataPlain);
		batchRequest.setBatch(batch);
		invoke(client.upsertPoints(collectionName, batchRequest, true));

		assertThat(client).hasPoints(collectionName, 1);
	}

	@Test
	@Override
	public void testUpsertPointsViaNamedBatch() throws Exception {
		PointsBatchUpsertRequest batchRequest = new PointsBatchUpsertRequest();
		PointsBatch batch = new PointsBatch();
		batch.setIds(9L);
		batch.setPayloads(Payload.of("{\"name\": \"fourth\"}"));
		BatchVectorDataMap vectorDataMap = new BatchVectorDataMap();
		vectorDataMap.put(VECTOR_NAME, Arrays.asList(Arrays.asList(41.5f, 56f, 1f, 0.42f)));
		batch.setVectors(vectorDataMap);
		batchRequest.setBatch(batch);
		invoke(client.upsertPoints(TEST_COLLECTION_NAME, batchRequest, true));

		assertThat(client).hasPoints(TEST_COLLECTION_NAME, 5);
	}

	@Test
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	public void testScrollPointsWithFilter() throws HttpErrorException {
		PointsScrollRequest request = new PointsScrollRequest();

		FieldCondition fieldCondition = new FieldCondition();
		fieldCondition.setKey("name");
		fieldCondition.setMatch(new MatchText().setText("third"));
		request.setFilter(new Filter().setMust(fieldCondition));
		request.setLimit(4);
		PointsScrollResponse resp = invoke(client.scrollPoints(TEST_COLLECTION_NAME, request));

		// Now assert that we only found the third point
		assertEquals(1, resp.getResult().getPoints().size());
		assertNull(resp.getResult().getNextPageOffset());
		assertEquals(PointId.id(3L), resp.getResult().getPoints().get(0).getId());
	}

	@Test
	public void testScrollPointsWithFilterOnIndexField() throws HttpErrorException {
		// Index the 'name' field
		CollectionCreateIndexFieldRequest indexRequest = new CollectionCreateIndexFieldRequest();
		indexRequest.setFieldName("name");
		indexRequest.setFieldSchema(PayloadIndexSchemaType.TEXT);
		invoke(client.createCollectionIndexField(TEST_COLLECTION_NAME, indexRequest, true));

		// Search for the field
		PointsScrollRequest request = new PointsScrollRequest();
		FieldCondition fieldCondition = new FieldCondition();
		fieldCondition.setKey("name");
		fieldCondition.setMatch(new MatchText().setText("third"));
		request.setFilter(new Filter().setMust(fieldCondition));
		request.setLimit(4);
		PointsScrollResponse resp = invoke(client.scrollPoints(TEST_COLLECTION_NAME, request));

		// Now assert that we only found the third point
		assertEquals(1, resp.getResult().getPoints().size());
		assertNull(resp.getResult().getNextPageOffset());
		assertEquals(PointId.id(3L), resp.getResult().getPoints().get(0).getId());
	}

	@Test
	@Override
	public void testSearchPoints() throws HttpErrorException {
		PointsSearchRequest request = new PointsSearchRequest();
		request.setVector(NamedVector.of(VECTOR_NAME, NEAR_VECTOR_1));
		request.setLimit(10);
		request.setWithPayload(true);
		request.setWithVector(true);
		PointsSearchResponse response = invoke(client.searchPoints(TEST_COLLECTION_NAME, request));
		assertFalse("The response should contain a result", response.getResult().isEmpty());
	}

	@Test
	@Override
	public void testSearchBatchPoints() throws HttpErrorException {
		PointsSearchRequest request = new PointsSearchRequest();
		request.setVector(NamedVector.of(VECTOR_NAME, NEAR_VECTOR_1));
		request.setLimit(10);
		request.setWithPayload(true);
		request.setWithVector(true);

		PointsSearchBatchRequest batchRequest = new PointsSearchBatchRequest();
		batchRequest.setSearches(Arrays.asList(request));
		PointsSearchBatchResponse response = invoke(client.searchBatchPoints(TEST_COLLECTION_NAME, batchRequest));
		assertEquals("There should be one result since we send a batch with one request.", 1, response.getResult().size());
		assertFalse(response.getResult().get(0).isEmpty());
	}

	@Test
	@Override
	public void testRecommendPoints() throws HttpErrorException {
		PointsRecommendRequest request = new PointsRecommendRequest();
		request.setPositive(1L);
		request.setUsing(VECTOR_NAME);
		request.setLimit(10);
		PointsRecommendResponse response = invoke(client.recommendPoints(TEST_COLLECTION_NAME, request));
		assertFalse("The response should contain recommended points", response.getResult().isEmpty());
	}

	@Test
	@Override
	public void testRecommendBatchPoints() throws HttpErrorException {
		PointsRecommendBatchRequest batchRequest = new PointsRecommendBatchRequest();
		PointsRecommendRequest request = new PointsRecommendRequest();
		request.setPositive(1L);
		request.setUsing(VECTOR_NAME);
		request.setLimit(10);
		batchRequest.setSearches(Arrays.asList(request));

		PointsRecommendBatchResponse response = invoke(client.recommendBatchPoints(TEST_COLLECTION_NAME, batchRequest));
		assertEquals("There should be one result since we sent one batched request.", 1, response.getResult().size());
		assertFalse("The response should contain recommended points", response.getResult().get(0).isEmpty());
	}

	@Test
	@Override
	public void testCountPoints() throws HttpErrorException {
		PointCountRequest request = new PointCountRequest();
		request.setExact(true);

		PointCountResponse response = invoke(client.countPoints(TEST_COLLECTION_NAME, request));
		assertEquals(4, response.getResult().getCount());
	}
}
