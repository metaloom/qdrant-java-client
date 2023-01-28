package io.metaloom.qdrant.client.http.model.collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.metaloom.qdrant.client.http.model.RestRequestModel;
import io.metaloom.qdrant.client.http.model.collection.config.CollectionParamsDiff;
import io.metaloom.qdrant.client.http.model.collection.config.OptimizersConfigDiff;

public class CollectionUpdateRequest implements RestRequestModel {

	@JsonProperty("optimizers_config")
	private OptimizersConfigDiff optimizersConfig;

	private CollectionParamsDiff params;

	public OptimizersConfigDiff getOptimizersConfig() {
		return optimizersConfig;
	}

	public CollectionParamsDiff getParams() {
		return params;
	}
}
