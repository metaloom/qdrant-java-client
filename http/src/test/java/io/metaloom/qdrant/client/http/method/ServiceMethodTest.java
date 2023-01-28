package io.metaloom.qdrant.client.http.method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.metaloom.qdrant.client.http.AbstractClientTest;
import io.metaloom.qdrant.client.http.impl.HttpErrorException;
import io.metaloom.qdrant.client.http.model.service.LockOptionResponse;

public class ServiceMethodTest extends AbstractClientTest {

	@Test
	public void testCollectTelemetryData() throws HttpErrorException {
		assertSuccess(client.collectTelemetry(true).sync());
	}

	@Test
	public void testLockOptions() throws HttpErrorException {
		// Check initial status
		LockOptionResponse before = client.getLockOptions().sync();
		assertSuccess(before);
		assertNull("Initially no error message should have been set", before.getResult().getErrorMessage());
		assertTrue("Initially the server should allow write operation", before.getResult().isWrite());

		// Lock the system
		assertSuccess(client.setLockOptions("the-message", true).sync());

		// Check after the change
		LockOptionResponse after = client.getLockOptions().sync();
		assertSuccess(after);
		assertEquals("the-message", after.getResult().getErrorMessage());
		assertFalse(after.getResult().isWrite());
	}
}
