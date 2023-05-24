package io.metaloom.qdrant.client.http.model;

import static io.metaloom.qdrant.client.http.model.collection.config.Distance.EUCLID;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import io.metaloom.qdrant.client.http.model.collection.CollectionCreateRequest;
import io.metaloom.qdrant.client.http.model.collection.CollectionResponse;
import io.metaloom.qdrant.client.http.model.collection.CollectionUpdateAliasesRequest;
import io.metaloom.qdrant.client.http.model.collection.CreateAliasOperation;
import io.metaloom.qdrant.client.http.model.collection.config.NamedVectorParams;
import io.metaloom.qdrant.client.http.model.collection.config.QuantizationConfig;
import io.metaloom.qdrant.client.http.model.collection.config.ScalarQuantization;
import io.metaloom.qdrant.client.http.model.collection.config.VectorParams;
import io.metaloom.qdrant.client.http.model.collection.config.VectorsConfig;
import io.metaloom.qdrant.client.http.model.collection.schema.CollectionCreateIndexFieldRequest;
import io.metaloom.qdrant.client.http.model.collection.schema.recommend.CollectionRecommendPointsResponse;
import io.metaloom.qdrant.client.json.Json;

public class CollectionModelTest extends AbstractModelTest {

	@Test
	public void testCollectionAliasUpdateRequestModel() {
		CollectionUpdateAliasesRequest request = load("collection/collection-alias-update-request", CollectionUpdateAliasesRequest.class);
		CreateAliasOperation createAlias = (CreateAliasOperation) request.getActions().get(0);
		assertEquals("theCollectionName", createAlias.getCreateAlias().getCollectionName());
		assertEquals("theAlias", createAlias.getCreateAlias().getAliasName());
	}

	@Test
	public void testCreateCollectionRequestModel() {
		CollectionCreateRequest request = new CollectionCreateRequest();
		NamedVectorParams params = new NamedVectorParams();
		params.put("test", VectorParams.of(4, EUCLID));
		params.put("test2", VectorParams.of(4, EUCLID));
		request.setVectors(params);

		String json = Json.parse(request);
		System.out.println(json);
		CollectionCreateRequest deserializedRequest = Json.parse(json, CollectionCreateRequest.class);
		VectorsConfig vectorsConfig = deserializedRequest.getVectors();
		System.out.println(vectorsConfig);
	}

	@Test
	public void testCollectionGetResponseModel() {
		CollectionResponse response = load("collection/collection-get-response", CollectionResponse.class);
		VectorsConfig vectorConfig = response.getResult().getConfig().getParams().getVectors();
		assertNotNull(vectorConfig);
		if (!(vectorConfig instanceof NamedVectorParams)) {
			fail("The vector config should be a named vector param config");
		}
	}

	@Test
	public void testCollectionGetResponseModelWithQuant() {
		CollectionResponse response = load("collection/collection-get-response-with-quantization_config", CollectionResponse.class);
		QuantizationConfig config = response.getResult().getConfig().getQuantizationConfig();
		assertNotNull(config);
		ScalarQuantization qConfig = (ScalarQuantization) config;
		assertEquals("int8", qConfig.getScalar().getType());
		String json = Json.parse(response);
		CollectionResponse fromJson = Json.parse(json, CollectionResponse.class);
		assertNotNull(fromJson.getResult().getConfig().getQuantizationConfig());
	}

	@Test
	public void testCreateCollectionRequestModel2() {
		CollectionCreateRequest request = new CollectionCreateRequest();
		VectorsConfig params = VectorParams.of(4, EUCLID);
		request.setVectors(params);

		String json = Json.parse(request);
		CollectionCreateRequest deserializedRequest = Json.parse(json, CollectionCreateRequest.class);
		VectorsConfig vectorsConfig = deserializedRequest.getVectors();
		assertNotNull(vectorsConfig);
	}

	@Test
	public void testCreateKeywordIndexFieldSchemaRequest() {
		CollectionCreateIndexFieldRequest request = load("collection/collection-create-keyword-index-field-request",
			CollectionCreateIndexFieldRequest.class);
		String json = Json.parse(request);
		System.out.println(json);
		Json.parse(json, CollectionCreateIndexFieldRequest.class);
	}

	@Test
	public void testCreateFullTextIndexFieldSchemaRequest() {
		CollectionCreateIndexFieldRequest request = load("collection/collection-create-full-text-index-field-request",
			CollectionCreateIndexFieldRequest.class);
		String json = Json.parse(request);
		System.out.println(json);
		Json.parse(json, CollectionCreateIndexFieldRequest.class);
	}

}
