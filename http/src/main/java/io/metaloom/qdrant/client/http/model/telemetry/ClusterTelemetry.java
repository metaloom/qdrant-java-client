package io.metaloom.qdrant.client.http.model.telemetry;

import io.metaloom.qdrant.client.http.model.RestModel;

public class ClusterTelemetry implements RestModel {

	private boolean enabled;

	private ClusterStatusTelemetry status;

	private ClusterConfigTelemetry config;

	public boolean isEnabled() {
		return enabled;
	}

	public ClusterStatusTelemetry getStatus() {
		return status;
	}

	public ClusterConfigTelemetry getConfig() {
		return config;
	}

}
