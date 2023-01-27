package io.metaloom.qdrant.client.http.model.telemetry;

import io.metaloom.qdrant.client.http.model.RestModel;

public class TelemetryData implements RestModel {

	private String id;

	private AppBuildTelemetry app;

	private CollectionsTelemetry collections;

	private ClusterTelemetry cluster;

	private RequestsTelemetry requests;

	public String getId() {
		return id;
	}

	public AppBuildTelemetry getApp() {
		return app;
	}

	public CollectionsTelemetry getCollections() {
		return collections;
	}

	public ClusterTelemetry getCluster() {
		return cluster;
	}

	public RequestsTelemetry getRequests() {
		return requests;
	}

}
