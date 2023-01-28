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

	public TelemetryData setId(String id) {
		this.id = id;
		return this;
	}

	public AppBuildTelemetry getApp() {
		return app;
	}

	public TelemetryData setApp(AppBuildTelemetry app) {
		this.app = app;
		return this;
	}

	public CollectionsTelemetry getCollections() {
		return collections;
	}

	public TelemetryData setCollections(CollectionsTelemetry collections) {
		this.collections = collections;
		return this;
	}

	public ClusterTelemetry getCluster() {
		return cluster;
	}

	public TelemetryData setCluster(ClusterTelemetry cluster) {
		this.cluster = cluster;
		return this;
	}

	public RequestsTelemetry getRequests() {
		return requests;
	}

	public TelemetryData setRequests(RequestsTelemetry requests) {
		this.requests = requests;
		return this;
	}

}
