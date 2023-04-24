package io.metaloom.qdrant.client.http.model.telemetry;

import io.metaloom.qdrant.client.http.model.RestModel;

public class AppBuildTelemetry implements RestModel {

	private String name;

	private String version;

	private AppFeaturesTelemetry features;

	private RunningEnvironmentTelemetry system;

	private String startup;

	public String getName() {
		return name;
	}

	public AppBuildTelemetry setName(String name) {
		this.name = name;
		return this;
	}

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

	public String getStartup() {
		return startup;
	}

	public AppBuildTelemetry setStartup(String startup) {
		this.startup = startup;
		return this;
	}
}
