package io.metaloom.qdrant.client.http.model.point;

import java.util.List;

import io.metaloom.qdrant.client.http.model.RestModel;

public class PointsBatch implements RestModel {

	private List<Long> ids;
	private List<List<Float>> vectors;
	private List<Payload> payloads;

	public List<Long> getIds() {
		return ids;
	}

	public List<List<Float>> getVectors() {
		return vectors;
	}

	public List<Payload> getPayloads() {
		return payloads;
	}
}
