package io.metaloom.qdrant.client.http.model.telemetry;

import java.util.Map;

import io.metaloom.qdrant.client.http.model.RestModel;

public class WebApiTelemetry implements RestModel {

	private Map<String, Map<String, OperationDurationStatistics>> responses;

	public Map<String, Map<String, OperationDurationStatistics>> getResponses() {
		return responses;
	}
}
