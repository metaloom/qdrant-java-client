package io.metaloom.qdrant.client.http.model.telemetry;

import io.metaloom.qdrant.client.http.model.RestModel;

public class RequestsTelemetry implements RestModel {

	private WebApiTelemetry rest;

	private GrpcTelemetry grpc;

	public GrpcTelemetry getGrpc() {
		return grpc;
	}

	public RequestsTelemetry setGrpc(GrpcTelemetry grpc) {
		this.grpc = grpc;
		return this;
	}

	public WebApiTelemetry getRest() {
		return rest;
	}

	public RequestsTelemetry setRest(WebApiTelemetry rest) {
		this.rest = rest;
		return this;
	}
}
