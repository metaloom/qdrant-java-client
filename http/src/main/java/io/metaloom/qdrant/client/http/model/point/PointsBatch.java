package io.metaloom.qdrant.client.http.model.point;

import static io.metaloom.qdrant.client.util.QDrantClientUtil.toList;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class PointsBatch implements RestModel {

	@JsonProperty("ids")
	private List<Long> ids;

	@JsonProperty("vectors")
	private List<Vector> vectors;

	@JsonProperty("payloads")
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

	@JsonIgnore
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

	@JsonIgnore
	public PointsBatch setIds(long... ids) {
		this.ids = toList(ids);
		return this;
	}

	@JsonIgnore
	public PointsBatch setPayloads(Payload... payloads) {
		this.payloads = Arrays.asList(payloads);
		return this;
	}
}
