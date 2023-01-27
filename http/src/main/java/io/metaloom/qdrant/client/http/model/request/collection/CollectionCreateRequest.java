package io.metaloom.qdrant.client.http.model.request.collection;

import io.metaloom.qdrant.client.http.model.collection.config.HnswConfigDiff;
import io.metaloom.qdrant.client.http.model.collection.config.OptimizersConfigDiff;
import io.metaloom.qdrant.client.http.model.collection.config.VectorsConfig;
import io.metaloom.qdrant.client.http.model.collection.config.WalConfigDiff;

public class CollectionCreateRequest {

	VectorsConfig vectors;

	Integer shard_number;

	Integer replication_factor;

	Integer write_consistency_factor;

	Boolean on_disk_payload;

	HnswConfigDiff hnsw_config;

	WalConfigDiff wal_config;

	OptimizersConfigDiff optimizers_config;
}
