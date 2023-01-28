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
	
	public AppFeaturesTelemetry setDebug(boolean debug) {
		this.debug = debug;
		return this;
	}

	public boolean isServiceDebugFeature() {
		return serviceDebugFeature;
	}

	public AppFeaturesTelemetry setServiceDebugFeature(boolean serviceDebugFeature) {
		this.serviceDebugFeature = serviceDebugFeature;
		return this;
	}

	public boolean isWebFeature() {
		return webFeature;
	}

	public AppFeaturesTelemetry setWebFeature(boolean webFeature) {
		this.webFeature = webFeature;
		return this;
	}
}
