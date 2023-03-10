package io.metaloom.qdrant.client.grpc.method;

import static io.metaloom.qdrant.client.grpc.InternalGrpcUtil.assertCollectionName;
import static io.metaloom.qdrant.client.grpc.InternalGrpcUtil.collectionsAsyncStub;
import static io.metaloom.qdrant.client.grpc.InternalGrpcUtil.collectionsStub;

import java.util.Arrays;
import java.util.Objects;

import io.metaloom.qdrant.client.ClientSettings;
import io.metaloom.qdrant.client.grpc.proto.Collections.AliasOperations;
import io.metaloom.qdrant.client.grpc.proto.Collections.ChangeAliases;
import io.metaloom.qdrant.client.grpc.proto.Collections.CollectionOperationResponse;
import io.metaloom.qdrant.client.grpc.proto.Collections.CollectionParamsDiff;
import io.metaloom.qdrant.client.grpc.proto.Collections.CreateCollection;
import io.metaloom.qdrant.client.grpc.proto.Collections.DeleteCollection;
import io.metaloom.qdrant.client.grpc.proto.Collections.GetCollectionInfoRequest;
import io.metaloom.qdrant.client.grpc.proto.Collections.GetCollectionInfoResponse;
import io.metaloom.qdrant.client.grpc.proto.Collections.HnswConfigDiff;
import io.metaloom.qdrant.client.grpc.proto.Collections.ListAliasesResponse;
import io.metaloom.qdrant.client.grpc.proto.Collections.ListCollectionAliasesRequest;
import io.metaloom.qdrant.client.grpc.proto.Collections.ListCollectionsRequest;
import io.metaloom.qdrant.client.grpc.proto.Collections.ListCollectionsResponse;
import io.metaloom.qdrant.client.grpc.proto.Collections.OptimizersConfigDiff;
import io.metaloom.qdrant.client.grpc.proto.Collections.UpdateCollection;
import io.metaloom.qdrant.client.grpc.proto.Collections.VectorParams;
import io.metaloom.qdrant.client.grpc.proto.Collections.VectorParamsMap;
import io.metaloom.qdrant.client.grpc.proto.Collections.VectorsConfig;
import io.metaloom.qdrant.client.grpc.proto.Collections.WalConfigDiff;

public interface CollectionMethods extends ClientSettings {

	/**
	 * Get list name of all existing collections.
	 * 
	 * @return
	 */
	default GrpcClientRequest<ListCollectionsResponse> listCollections() {
		ListCollectionsRequest request = ListCollectionsRequest.newBuilder().build();
		return request(
			() -> collectionsStub(this).list(request),
			() -> collectionsAsyncStub(this).list(request));
	}

	/**
	 * Get detailed information about specified existing collection
	 * 
	 * @param collectionName
	 *            Name of the collection to retrieve
	 * @return
	 */
	default GrpcClientRequest<GetCollectionInfoResponse> loadCollections(String collectionName) {
		assertCollectionName(collectionName);

		GetCollectionInfoRequest request = GetCollectionInfoRequest.newBuilder()
			.setCollectionName(collectionName)
			.build();

		return request(
			() -> collectionsStub(this).get(request),
			() -> collectionsAsyncStub(this).get(request));
	}

	/**
	 * Create new collection with given parameters.
	 * 
	 * @param collectionName
	 *            Name of the new collection
	 * @param params
	 *            Vector parameters for the new collection (e.g. dimension, method of distance computation)
	 * @return
	 */
	default GrpcClientRequest<CollectionOperationResponse> createCollection(String collectionName, VectorParams params) {
		VectorsConfig config = VectorsConfig.newBuilder().setParams(params).build();
		return createCollection(collectionName, config, null, null, null, null, null, null, null, null);
	}

	default GrpcClientRequest<CollectionOperationResponse> createCollection(String collectionName, VectorParamsMap paramsMap) {
		VectorsConfig config = VectorsConfig.newBuilder().setParamsMap(paramsMap).build();
		return createCollection(collectionName, config, null, null, null, null, null, null, null, null);
	}

	/**
	 * Create new collection with given parameters.
	 * 
	 * @param collectionName
	 *            Name of the new collection
	 * @param config
	 *            Vector configuration for the new collection which contains vector params (e.g. dimension, method of distance computation)
	 * @param shardNumber
	 *            Number of shards in collection. Default is 1 for standalone, otherwise equal to the number of nodes Minimum is 1
	 * @param replicationFactor
	 *            Number of shards replicas. Default is 1 Minimum is 1
	 * @param writeConsistencyFactor
	 *            Defines how many replicas should apply the operation for us to consider it successful. Increasing this number will make the collection more
	 *            resilient to inconsistencies, but will also make it fail if not enough replicas are available. Does not have any performance impact.
	 * @param onDiskPayload
	 *            If true - point's payload will not be stored in memory. It will be read from the disk every time it is requested. This setting saves RAM by
	 *            (slightly) increasing the response time. Note: those payload values that are involved in filtering and are indexed - remain in RAM.
	 * @param hnswConfig
	 *            Custom params for HNSW index. If none - values from service configuration file are used.
	 * @param walConfig
	 *            Custom params for WAL. If none - values from service configuration file are used.
	 * @param optimizersConfig
	 *            Custom params for Optimizers. If none - values from service configuration file are used.
	 * @param timeout
	 *            Wait for operation commit timeout in seconds. If timeout is reached - request will return with service error.
	 * 
	 * @return
	 */
	default GrpcClientRequest<CollectionOperationResponse> createCollection(String collectionName, VectorsConfig config, Integer shardNumber,
		Integer replicationFactor, Integer writeConsistencyFactor, Boolean onDiskPayload, HnswConfigDiff hnswConfig, WalConfigDiff walConfig,
		OptimizersConfigDiff optimizersConfig, Integer timeout) {
		assertCollectionName(collectionName);

		CreateCollection.Builder request = CreateCollection.newBuilder()
			.setCollectionName(collectionName)
			.setVectorsConfig(config);

		if (shardNumber != null) {
			request.setShardNumber(shardNumber);
		}
		if (replicationFactor != null) {
			request.setReplicationFactor(replicationFactor);
		}
		if (writeConsistencyFactor != null) {
			request.setWriteConsistencyFactor(writeConsistencyFactor);
		}
		if (onDiskPayload != null) {
			request.setOnDiskPayload(onDiskPayload);
		}
		if (hnswConfig != null) {
			request.setHnswConfig(hnswConfig);
		}
		if (walConfig != null) {
			request.setWalConfig(walConfig);
		}
		if (optimizersConfig != null) {
			request.setOptimizersConfig(optimizersConfig);
		}

		return request(
			() -> collectionsStub(this).create(request.build()),
			() -> collectionsAsyncStub(this).create(request.build()));
	}

	/**
	 * Drop collection and all associated data
	 * 
	 * @param collectionName
	 *            Name of the collection to delete
	 * @param timeout
	 *            Wait for operation commit timeout in seconds. If timeout is reached - request will return with service error.
	 * @return
	 */
	default GrpcClientRequest<CollectionOperationResponse> deleteCollection(String collectionName, Integer timeout) {
		assertCollectionName(collectionName);

		DeleteCollection.Builder request = DeleteCollection.newBuilder()
			.setCollectionName(collectionName);

		if (timeout != null) {
			request.setTimeout(timeout);
		}

		return request(
			() -> collectionsStub(this).delete(request.build()),
			() -> collectionsAsyncStub(this).delete(request.build()));
	}

	/**
	 * Update aliases of the collections.
	 * 
	 * @param timeout
	 *            Wait for operation commit timeout in seconds. If timeout is reached - request will return with service error.
	 * @param actions
	 *            Alias update operations (create,delete,rename)
	 * @return
	 */
	default GrpcClientRequest<CollectionOperationResponse> updateCollectionAliases(Integer timeout, AliasOperations... actions) {
		Objects.requireNonNull(actions, "Actions for the update operation must be specified.");

		ChangeAliases.Builder request = ChangeAliases.newBuilder()
			.addAllActions(Arrays.asList(actions));

		if (timeout != null) {
			request.setTimeout(timeout);
		}

		return request(
			() -> collectionsStub(this).updateAliases(request.build()),
			() -> collectionsAsyncStub(this).updateAliases(request.build()));
	}

	/**
	 * Get list of all aliases for a collection.
	 * 
	 * @param collectionName
	 * @return
	 */
	default GrpcClientRequest<ListAliasesResponse> listCollectionAliases(String collectionName) {
		assertCollectionName(collectionName);

		ListCollectionAliasesRequest.Builder request = ListCollectionAliasesRequest.newBuilder()
			.setCollectionName(collectionName);

		return request(
			() -> collectionsStub(this).listCollectionAliases(request.build()),
			() -> collectionsAsyncStub(this).listCollectionAliases(request.build()));
	}

	/**
	 * Update parameters of the existing collection
	 * 
	 * @param collectionName
	 *            Name of the collection to update
	 * @param paramConfig
	 *            Collection base params. If none - values from service configuration file are used.
	 * @return
	 */
	default GrpcClientRequest<CollectionOperationResponse> updateCollection(String collectionName, CollectionParamsDiff paramConfig) {
		return updateCollection(collectionName, paramConfig, null, null);
	}

	/**
	 * Update parameters of the existing collection
	 * 
	 * @param collectionName
	 *            Name of the collection to update
	 * @param paramConfig
	 *            Collection base params. If none - values from service configuration file are used.
	 * @param optimizerConfig
	 *            Custom params for Optimizers. If none - values from service configuration file are used. This operation is blocking, it will only proceed ones
	 *            all current optimizations are complete
	 * @param timeout
	 *            Wait for operation commit timeout in seconds. If timeout is reached - request will return with service error.
	 * @return
	 */
	default GrpcClientRequest<CollectionOperationResponse> updateCollection(String collectionName, CollectionParamsDiff paramConfig,
		OptimizersConfigDiff optimizerConfig, Integer timeout) {
		assertCollectionName(collectionName);

		UpdateCollection.Builder request = UpdateCollection.newBuilder()
			.setCollectionName(collectionName);

		if (optimizerConfig != null) {
			request.setOptimizersConfig(optimizerConfig);
		}
		if (paramConfig != null) {
			request.setParams(paramConfig);
		}
		if (timeout != null) {
			request.setTimeout(timeout);
		}

		return request(
			() -> collectionsStub(this).update(request.build()),
			() -> collectionsAsyncStub(this).update(request.build()));
	}

}
