package io.metaloom.qdrant.client.http.model.point;

import static io.metaloom.qdrant.client.util.QDrantClientUtil.toList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;

import io.metaloom.qdrant.client.http.model.RestModel;
import io.metaloom.qdrant.client.json.Json;

public class PointStruct implements RestModel {

	@JsonProperty("id")
	private int id;

	@JsonProperty("vector")
	private VectorData vector;

	@JsonProperty("payload")
	private Payload payload;

	public int getId() {
		return id;
	}

	public PointStruct setId(int id) {
		this.id = id;
		return this;
	}

	public VectorData getVector() {
		return vector;
	}

	public PointStruct setVector(VectorData vector) {
		this.vector = vector;
		return this;
	}

	@JsonIgnore
	public PointStruct setVector(float... vector) {
		VectorDataPlain data = new VectorDataPlain();
		data.setVector(toList(vector));
		this.vector = data;
		return this;
	}

	public Payload getPayload() {
		return payload;
	}

	public PointStruct setPayload(Payload payload) {
		this.payload = payload;
		return this;
	}

	@JsonIgnore
	public PointStruct setPayload(JsonNode json) {
		this.payload = new Payload().setJson(json);
		return this;
	}

	@JsonIgnore
	public PointStruct setPayload(String json) throws JacksonException {
		setPayload(Json.toJson(json));
		return this;
	}

	@JsonIgnore
	public static PointStruct of(float... vectorComponent) {
		PointStruct p = new PointStruct();
		p.setVector(vectorComponent);
		return p;
	}
}