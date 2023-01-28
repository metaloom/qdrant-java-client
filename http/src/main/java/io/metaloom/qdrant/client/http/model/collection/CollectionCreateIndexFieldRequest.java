package io.metaloom.qdrant.client.http.model.collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestRequestModel;

public class CollectionCreateIndexFieldRequest implements RestRequestModel {

	@JsonProperty("field_name")
	private String fieldName;

	@JsonProperty("field_name")
	private PayloadFieldSchema fieldSchema;

	public String getFieldName() {
		return fieldName;
	}

	public CollectionCreateIndexFieldRequest setFieldName(String fieldName) {
		this.fieldName = fieldName;
		return this;
	}

	public PayloadFieldSchema getFieldSchema() {
		return fieldSchema;
	}

	public CollectionCreateIndexFieldRequest setFieldSchema(PayloadFieldSchema fieldSchema) {
		this.fieldSchema = fieldSchema;
		return this;
	}
}
