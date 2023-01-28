package io.metaloom.qdrant.client.http.model.point;

import static io.metaloom.qdrant.client.util.QDrantClientUtil.toList;

import java.util.List;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;

import io.metaloom.qdrant.client.http.model.RestModel;
import io.metaloom.qdrant.client.json.Json;

public class PointStruct implements RestModel {

	private int id;
	private List<Float> vector;
	private Payload payload;

	public int getId() {
		return id;
	}

	public PointStruct setId(int id) {
		this.id = id;
		return this;
	}

	public List<Float> getVector() {
		return vector;
	}

	public PointStruct setVector(List<Float> vector) {
		this.vector = vector;
		return this;
	}

	public PointStruct setVector(float... vector) {
		this.vector = toList(vector);
		return this;
	}

	public Payload getPayload() {
		return payload;
	}

	public PointStruct setPayload(Payload payload) {
		this.payload = payload;
		return this;
	}

	public PointStruct setPayload(JsonNode json) {
		this.payload = new Payload().setJson(json);
		return this;
	}

	public PointStruct setPayload(String json) throws JacksonException {
		setPayload(Json.toJson(json));
		return this;
	}

	public static PointStruct of(float... vectorComponent) {
		PointStruct p = new PointStruct();
		p.setVector(vectorComponent);
		return p;
	}
}
