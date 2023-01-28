package io.metaloom.qdrant.client.http.model.telemetry;

import io.metaloom.qdrant.client.http.model.RestModel;

public class ClusterTelemetry implements RestModel {

	private boolean enabled;

	private ClusterStatusTelemetry status;

	private ClusterConfigTelemetry config;

	public boolean isEnabled() {
		return enabled;
	}

	public ClusterTelemetry setEnabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}

	public ClusterStatusTelemetry getStatus() {
		return status;
	}

	public ClusterTelemetry setStatus(ClusterStatusTelemetry status) {
		this.status = status;
		return this;
	}

	public ClusterConfigTelemetry getConfig() {
		return config;
	}

	public ClusterTelemetry setConfig(ClusterConfigTelemetry config) {
		this.config = config;
		return this;
	}

}
