package io.metaloom.qdrant.client.http.model.telemetry;

import io.metaloom.qdrant.client.http.model.RestModel;
import io.metaloom.qdrant.client.http.model.collection.config.OptimizersStatus;

public class OptimizerTelemetry implements RestModel {

	private OptimizersStatus status;

	private OperationDurationStatistics optimizations;

	public OptimizersStatus getStatus() {
		return status;
	}

	public OperationDurationStatistics getOptimizations() {
		return optimizations;
	}
}
