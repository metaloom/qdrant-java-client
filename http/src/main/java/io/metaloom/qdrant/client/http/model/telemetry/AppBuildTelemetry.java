package io.metaloom.qdrant.client.http.model.telemetry;

import io.metaloom.qdrant.client.http.model.RestModel;

public class AppBuildTelemetry implements RestModel {

	private String version;

	private AppFeaturesTelemetry features;

	private RunningEnvironmentTelemetry system;

	public String getVersion() {
		return version;
	}

	public AppFeaturesTelemetry getFeatures() {
		return features;
	}

	public RunningEnvironmentTelemetry getSystem() {
		return system;
	}
}
