package io.metaloom.qdrant.client.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

import io.metaloom.qdrant.client.AbstractContainerTest;
import io.metaloom.qdrant.client.http.impl.HttpErrorException;
import io.metaloom.qdrant.client.http.model.AbstractResponse;
import io.metaloom.qdrant.client.http.model.GenericBooleanStatusResponse;
import io.metaloom.qdrant.client.http.model.RestResponse;

public abstract class AbstractClientTest extends AbstractContainerTest {

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

	protected void assertSuccess(GenericBooleanStatusResponse response) {
		assertTrue("The response should be successful.", response.getResult());
	}

	protected void assertSuccess(AbstractResponse response) {
		assertEquals("The response should be successful.", "ok", response.getStatus());
	}

	protected <T extends RestResponse> T invoke(QDrantClientRequest<T> request) throws HttpErrorException {
		T response = request.sync();
		assertSuccess((AbstractResponse) response);
		return response;
	}

}
