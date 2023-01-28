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

	public PointsBatch setIds(List<Long> ids) {
		this.ids = ids;
		return this;
	}

	public List<List<Float>> getVectors() {
		return vectors;
	}

	public PointsBatch setVectors(List<List<Float>> vectors) {
		this.vectors = vectors;
		return this;
	}

	public List<Payload> getPayloads() {
		return payloads;
	}

	public PointsBatch setPayloads(List<Payload> payloads) {
		this.payloads = payloads;
		return this;
	}
}
