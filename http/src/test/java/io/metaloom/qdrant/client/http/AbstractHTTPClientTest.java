package io.metaloom.qdrant.client.http;

import static io.metaloom.qdrant.client.http.model.collection.config.Distance.EUCLID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;

import io.metaloom.qdrant.client.AbstractContainerTest;
import io.metaloom.qdrant.client.http.impl.HttpErrorException;
import io.metaloom.qdrant.client.http.model.AbstractResponse;
import io.metaloom.qdrant.client.http.model.ErrorResponse;
import io.metaloom.qdrant.client.http.model.GenericBooleanStatusResponse;
import io.metaloom.qdrant.client.http.model.RestResponse;
import io.metaloom.qdrant.client.http.model.collection.CollectionCreateRequest;
import io.metaloom.qdrant.client.http.model.collection.config.VectorParams;

public abstract class AbstractHTTPClientTest extends AbstractContainerTest {

	public static String VECTOR_NAME = "test-points";
	public static float[] VECTOR_1 = { 0.42f, 0.33f, 42.15f, 68.72f };
	public static float[] NEAR_VECTOR_1 = { 0.41f, 0.32f, 42.11f, 68.71f };
	public static float[] VECTOR_2 = { 0.12f, 0.23f, 12.46f, 47.17f };
	public static float[] VECTOR_3 = { 0.76f, 0.43f, 63.45f, 22.10f };
	public static float[] VECTOR_4 = { 0.17f, 0.36f, 72.39f, 0.0f };

	public static final String TEST_COLLECTION_NAME = "the-test-collection";

	protected QDrantHttpClient client;

	@Before
	public void prepareClient() {
		client = QDrantHttpClient.builder()
			.setScheme("http")
			.setHostname("localhost")
			.setPort(qdrant.httpPort())
			.build();
	}

	@After
	public void closeClient() {
		if (client != null) {
			client.close();
		}
	}

	/**
	 * Return JSON with name property and specified value.
	 * 
	 * @param name
	 * @return
	 */
	protected String json(String key, String name) {
		return "{ \"" + key + "\": \"" + name + "\"}";
	}

	protected void assertSuccess(GenericBooleanStatusResponse response) {
		assertTrue("The response should be successful.", response.getResult());
	}

	protected void assertSuccess(AbstractResponse response) {
		assertEquals("The response should be successful.", "ok", response.getStatus());
	}

	protected <T extends RestResponse> T invoke(QDrantClientRequest<T> request) throws HttpErrorException {
		try {
			T response = request.sync();
			assertSuccess((AbstractResponse) response);
			return response;
		} catch (HttpErrorException e) {
			ErrorResponse error = e.getError();
			fail("Request failed with error " + error.getStatus().getError());
			return null;
		}
	}

	protected void createTestCollection() throws HttpErrorException {
		CollectionCreateRequest request = new CollectionCreateRequest();
		request.setVectors(VectorParams.of(4, EUCLID));
		invoke(client.createCollection(TEST_COLLECTION_NAME, request));
	}

}
