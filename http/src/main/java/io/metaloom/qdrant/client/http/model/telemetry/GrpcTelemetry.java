package io.metaloom.qdrant.client.http.model.telemetry;

import java.util.Map;

import io.metaloom.qdrant.client.http.model.RestModel;

public class GrpcTelemetry implements RestModel {

	private Map<String, OperationDurationStatistics> responses;

	public Map<String, OperationDurationStatistics> getResponses() {
		return responses;
	}
}
