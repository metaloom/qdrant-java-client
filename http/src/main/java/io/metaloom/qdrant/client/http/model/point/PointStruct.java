package io.metaloom.qdrant.client.http.model.point;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;

import io.metaloom.qdrant.client.http.model.RestModel;
import io.metaloom.qdrant.json.Json;

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
		List<Float> result = new ArrayList<>(vector.length);
		for (float f : vector) {
			result.add(f);
		}
		this.vector = result;
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
}
