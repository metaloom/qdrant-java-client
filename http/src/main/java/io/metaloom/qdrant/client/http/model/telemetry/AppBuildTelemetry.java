package io.metaloom.qdrant.client.http.model.telemetry;

import io.metaloom.qdrant.client.http.model.RestModel;

public class AppBuildTelemetry implements RestModel {

	private String version;

	private AppFeaturesTelemetry features;

	private RunningEnvironmentTelemetry system;

	public String getVersion() {
		return version;
	}

	public AppBuildTelemetry setVersion(String version) {
		this.version = version;
		return this;
	}

	public AppFeaturesTelemetry getFeatures() {
		return features;
	}

	public AppBuildTelemetry setFeatures(AppFeaturesTelemetry features) {
		this.features = features;
		return this;
	}

	public RunningEnvironmentTelemetry getSystem() {
		return system;
	}

	public AppBuildTelemetry setSystem(RunningEnvironmentTelemetry system) {
		this.system = system;
		return this;
	}
}
