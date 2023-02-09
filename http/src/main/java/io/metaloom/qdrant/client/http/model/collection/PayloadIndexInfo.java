package io.metaloom.qdrant.client.http.model.collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;
import io.metaloom.qdrant.client.http.model.collection.schema.FullTextIndexFieldSchema;
import io.metaloom.qdrant.client.http.model.collection.schema.PayloadIndexSchemaType;

public class PayloadIndexInfo implements RestModel {

	@JsonProperty("data_type")
	private PayloadIndexSchemaType dataType;

	private FullTextIndexFieldSchema params;

	private int points;

	public PayloadIndexSchemaType getDataType() {
		return dataType;
	}

	public PayloadIndexInfo setDataType(PayloadIndexSchemaType dataType) {
		this.dataType = dataType;
		return this;
	}

	public FullTextIndexFieldSchema getParams() {
		return params;
	}

	public PayloadIndexInfo setParams(FullTextIndexFieldSchema params) {
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
