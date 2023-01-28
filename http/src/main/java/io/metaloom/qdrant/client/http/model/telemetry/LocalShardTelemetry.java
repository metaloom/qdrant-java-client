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

	public LocalShardTelemetry setVariantName(String variantName) {
		this.variantName = variantName;
		return this;
	}

	public List<SegmentTelemetry> getSegments() {
		return segments;
	}

	public LocalShardTelemetry setSegments(List<SegmentTelemetry> segments) {
		this.segments = segments;
		return this;
	}

	public OptimizerTelemetry getOptimizations() {
		return optimizations;
	}

	public LocalShardTelemetry setOptimizations(OptimizerTelemetry optimizations) {
		this.optimizations = optimizations;
		return this;
	}

}
