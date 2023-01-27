package io.metaloom.qdrant.client.http.model.telemetry;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestModel;

public class CollectionsTelemetry implements RestModel {

	@JsonProperty("number_of_collections")
	private int numberOfCollections;

	private List<CollectionTelemetry> collections;

	public int getNumberOfCollections() {
		return numberOfCollections;
	}

	public List<CollectionTelemetry> getCollections() {
		return collections;
	}
}
