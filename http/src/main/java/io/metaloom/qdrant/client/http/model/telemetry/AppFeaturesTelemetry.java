package io.metaloom.qdrant.client.http.model.telemetry;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class AppFeaturesTelemetry implements RestModel {

	private boolean debug;

	@JsonProperty("web_feature")
	private boolean webFeature;

	@JsonProperty("service_debug_feature")
	private boolean serviceDebugFeature;

	public boolean isDebug() {
		return debug;
	}

	public boolean isServiceDebugFeature() {
		return serviceDebugFeature;
	}

	public boolean isWebFeature() {
		return webFeature;
	}

}
