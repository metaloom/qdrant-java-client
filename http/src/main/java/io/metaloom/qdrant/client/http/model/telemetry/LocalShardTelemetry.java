package io.metaloom.qdrant.client.http.model.telemetry;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class LocalShardTelemetry implements RestModel {

	@JsonProperty("variant_name")
	private String variantName;

	private List<SegmentTelemetry> segments;

	private OptimizerTelemetry optimizations;

	public String getVariantName() {
		return variantName;
	}

	public List<SegmentTelemetry> getSegments() {
		return segments;
	}

	public OptimizerTelemetry getOptimizations() {
		return optimizations;
	}

}
