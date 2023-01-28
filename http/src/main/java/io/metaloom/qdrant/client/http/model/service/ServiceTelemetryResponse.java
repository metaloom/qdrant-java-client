package io.metaloom.qdrant.client.http.model.service;

import io.metaloom.qdrant.client.http.model.AbstractResponse;
import io.metaloom.qdrant.client.http.model.telemetry.TelemetryData;

public class ServiceTelemetryResponse extends AbstractResponse {

	private TelemetryData result;

	public TelemetryData getResult() {
		return result;
	}

	public ServiceTelemetryResponse setResult(TelemetryData result) {
		this.result = result;
		return this;
	}
}
