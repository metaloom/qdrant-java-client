package io.metaloom.qdrant.client.http.model;

import org.junit.Test;

import io.metaloom.qdrant.client.http.model.service.ServiceTelemetryResponse;

public class ServiceModelTest extends AbstractModelTest {

	@Test
	public void testTelemetryResponse() {
		load("service/telemetry-response", ServiceTelemetryResponse.class);
	}

	@Test
	public void testTelemetryResponse2() {
		load("service/telemetry-response-2", ServiceTelemetryResponse.class);
	}
}
