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

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public PayloadFieldSchema getFieldSchema() {
		return fieldSchema;
	}

	public void setFieldSchema(PayloadFieldSchema fieldSchema) {
		this.fieldSchema = fieldSchema;
	}
}
