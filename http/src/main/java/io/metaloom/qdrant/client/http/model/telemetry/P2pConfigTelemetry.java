package io.metaloom.qdrant.client.http.model.telemetry;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class P2pConfigTelemetry implements RestModel {

	@JsonProperty("connection_pool_size")
	private int connectionPoolSize;

	public int getConnectionPoolSize() {
		return connectionPoolSize;
	}

}
