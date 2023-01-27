package io.metaloom.qdrant.client.http.model.request.collection;

import io.metaloom.qdrant.client.http.model.collection.config.CollectionParamsDiff;
import io.metaloom.qdrant.client.http.model.collection.config.OptimizersConfigDiff;

public class CollectionUpdateRequest {

	OptimizersConfigDiff optimizers_config;
	CollectionParamsDiff params;
}
