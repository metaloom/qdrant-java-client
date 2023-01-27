package io.metaloom.qdrant.client.http.model.telemetry;

import io.metaloom.qdrant.client.http.model.RestModel;

public class RequestsTelemetry implements RestModel {

	private WebApiTelemetry rest;

	private GrpcTelemetry grpc;

	public GrpcTelemetry getGrpc() {
		return grpc;
	}

	public WebApiTelemetry getRest() {
		return rest;
	}
}
