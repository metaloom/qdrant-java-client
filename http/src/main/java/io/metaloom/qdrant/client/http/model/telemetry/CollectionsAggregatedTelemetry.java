package io.metaloom.qdrant.client.http.model.telemetry;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.collection.config.CollectionParams;
import io.metaloom.qdrant.client.http.model.collection.config.OptimizersStatus;

public class CollectionsAggregatedTelemetry implements CollectionTelemetry {

	private int vectors;

	@JsonProperty("optimizers_status")
	private OptimizersStatus optimizersStatus;

	private CollectionParams params;

	public int getVectors() {
		return vectors;
	}

	public OptimizersStatus getOptimizersStatus() {
		return optimizersStatus;
	}

	public CollectionParams getParams() {
		return params;
	}
}
