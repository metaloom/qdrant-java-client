package io.metaloom.qdrant.client.http.model;

import org.junit.Test;

import io.metaloom.qdrant.client.http.model.service.ServiceTelemetryResponse;

public class ServiceModelTest extends AbstractModelTest{

	@Test
	public void testTelemetryResponse() {
		ServiceTelemetryResponse resp = load("service/telemetry-response", ServiceTelemetryResponse.class);
	}
}
