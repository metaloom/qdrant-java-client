package io.metaloom.qdrant.client.http.model.collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;
import io.metaloom.qdrant.client.http.model.collection.config.CollectionConfig;

public class CollectionInfo implements RestModel {

	@JsonProperty("status")
	private CollectionStatus status;

	@JsonProperty("optimizer_status")
	private String optimizerStatus;

	@JsonProperty("vectors_count")
	private int vectorsCount;

	@JsonProperty("indexed_vectors_count")
	private int indexedVectorsCount;

	@JsonProperty("points_count")
	private int pointsCount;

	@JsonProperty("segments_count")
	private int segmentsCount;

	private CollectionConfig config;

	@JsonProperty("payload_schema")
	private PayloadIndexInfo payloadSchema;

	public CollectionStatus getStatus() {
		return status;
	}

	public CollectionInfo setStatus(CollectionStatus status) {
		this.status = status;
		return this;
	}

	public String getOptimizerStatus() {
		return optimizerStatus;
	}

	public CollectionInfo setOptimizerStatus(String optimizerStatus) {
		this.optimizerStatus = optimizerStatus;
		return this;
	}

	public int getVectorsCount() {
		return vectorsCount;
	}

	public CollectionInfo setVectorsCount(int vectorsCount) {
		this.vectorsCount = vectorsCount;
		return this;
	}

	public int getIndexedVectorsCount() {
		return indexedVectorsCount;
	}

	public CollectionInfo setIndexedVectorsCount(int indexedVectorsCount) {
		this.indexedVectorsCount = indexedVectorsCount;
		return this;
	}

	public CollectionConfig getConfig() {
		return config;
	}

	public CollectionInfo setConfig(CollectionConfig config) {
		this.config = config;
		return this;
	}

	public PayloadIndexInfo getPayloadSchema() {
		return payloadSchema;
	}

	public CollectionInfo setPayloadSchema(PayloadIndexInfo payloadSchema) {
		this.payloadSchema = payloadSchema;
		return this;
	}

	public int getPointsCount() {
		return pointsCount;
	}

	public CollectionInfo setPointsCount(int pointsCount) {
		this.pointsCount = pointsCount;
		return this;
	}

	public int getSegmentsCount() {
		return segmentsCount;
	}

	public CollectionInfo setSegmentsCount(int segmentsCount) {
		this.segmentsCount = segmentsCount;
		return this;
	}

}
