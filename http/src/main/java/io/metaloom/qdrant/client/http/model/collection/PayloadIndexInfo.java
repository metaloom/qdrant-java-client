package io.metaloom.qdrant.client.http.model.collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class PayloadIndexInfo implements RestModel {

	@JsonProperty("data_type")
	private PayloadSchemaType dataType;

	private PayloadSchemaParams params;

	private int points;

	public PayloadSchemaType getDataType() {
		return dataType;
	}

	public PayloadIndexInfo setDataType(PayloadSchemaType dataType) {
		this.dataType = dataType;
		return this;
	}

	public PayloadSchemaParams getParams() {
		return params;
	}

	public PayloadIndexInfo setParams(PayloadSchemaParams params) {
		this.params = params;
		return this;
	}

	public int getPoints() {
		return points;
	}

	public PayloadIndexInfo setPoints(int points) {
		this.points = points;
		return this;
	}
}
