package io.metaloom.qdrant.client.http.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.metaloom.qdrant.client.http.model.collection.CollectionCreateRequest;
import io.metaloom.qdrant.client.http.model.collection.CollectionUpdateAliasesRequest;
import io.metaloom.qdrant.client.http.model.collection.CreateAliasOperation;
import io.metaloom.qdrant.client.http.model.collection.config.NamedVectorParams;
import io.metaloom.qdrant.client.http.model.collection.config.VectorParams;
import io.metaloom.qdrant.client.http.model.collection.config.VectorsConfig;
import io.metaloom.qdrant.client.json.Json;

import static io.metaloom.qdrant.client.http.model.collection.config.Distance.EUCLID;

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
	public void testCreateCollectionRequestModel2() {
		CollectionCreateRequest request = new CollectionCreateRequest();
		VectorsConfig params = VectorParams.of(4, EUCLID);
		request.setVectors(params);

		String json = Json.parse(request);
		System.out.println(json);
		CollectionCreateRequest deserializedRequest = Json.parse(json, CollectionCreateRequest.class);
		VectorsConfig vectorsConfig = deserializedRequest.getVectors();
		System.out.println(vectorsConfig);
	}
}
