package io.metaloom.qdrant.client.http.method;

import static io.metaloom.qdrant.client.http.model.collection.config.Distance.EUCLID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import io.metaloom.qdrant.client.http.AbstractClientTest;
import io.metaloom.qdrant.client.http.impl.HttpErrorException;
import io.metaloom.qdrant.client.http.model.collection.CollectionCreateRequest;
import io.metaloom.qdrant.client.http.model.collection.config.VectorParams;
import io.metaloom.qdrant.client.http.model.service.LockOptionResponse;

public class ServiceMethodTest extends AbstractClientTest {

	@Test
	public void testCollectTelemetryData() throws HttpErrorException {
		invoke(client.collectTelemetry(true));
	}

	@Test
	public void testLockOptions() throws HttpErrorException {
		// Check initial status
		LockOptionResponse before = client.getLockOptions().sync();
		assertSuccess(before);
		assertNull("Initially no error message should have been set", before.getResult().getErrorMessage());
		assertFalse("Initially the server should allow write operation", before.getResult().isWrite());

		// Lock the system
		invoke(client.setLockOptions("the-message", true));

		// Check after the change
		LockOptionResponse after = invoke(client.getLockOptions());
		assertEquals("the-message", after.getResult().getErrorMessage());
		assertTrue(after.getResult().isWrite());

		// Try creating a collection
		try {
			CollectionCreateRequest request = new CollectionCreateRequest();
			request.setVectors(VectorParams.of(4, EUCLID));
			client.createCollection(TEST_COLLECTION_NAME, request).sync();
			fail("The request should have failed due to the lock");
		} catch (HttpErrorException e) {
			assertEquals("Storage locked: the-message", e.getError().getStatus().getError());
		}

	}
}
