package io.metaloom.qdrant.client.http.model.service;

import java.util.List;

import io.metaloom.qdrant.client.http.model.AbstractResponse;
import io.metaloom.qdrant.client.http.model.telemetry.TelemetryData;

public class ServiceTelemetryResponse extends AbstractResponse {

	private List<TelemetryData> result;

	public List<TelemetryData> getResult() {
		return result;
	}
}
