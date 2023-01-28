package io.metaloom.qdrant.client.http.model.point;

import static io.metaloom.qdrant.client.util.QDrantClientUtil.toList;

import java.util.Arrays;
import java.util.List;

import io.metaloom.qdrant.client.http.model.RestModel;

public class PointsBatch implements RestModel {

	private List<Long> ids;
	private List<Vector> vectors;
	private List<Payload> payloads;

	public List<Long> getIds() {
		return ids;
	}

	public PointsBatch setIds(List<Long> ids) {
		this.ids = ids;
		return this;
	}

	public List<Vector> getVectors() {
		return vectors;
	}

	public PointsBatch setVectors(List<Vector> vectors) {
		this.vectors = vectors;
		return this;
	}

	public PointsBatch setVectors(Vector... vectors) {
		this.vectors = Arrays.asList(vectors);
		return this;
	}

	public List<Payload> getPayloads() {
		return payloads;
	}

	public PointsBatch setPayloads(List<Payload> payloads) {
		this.payloads = payloads;
		return this;
	}

	public PointsBatch setIds(long... ids) {
		this.ids = toList(ids);
		return this;
	}

	public PointsBatch setPayloads(Payload... payloads) {
		this.payloads = Arrays.asList(payloads);
		return this;
	}
}
