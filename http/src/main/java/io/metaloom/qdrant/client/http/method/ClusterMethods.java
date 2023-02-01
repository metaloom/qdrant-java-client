package io.metaloom.qdrant.client.http.method;

import io.metaloom.qdrant.client.http.QDrantClientRequest;
import io.metaloom.qdrant.client.http.model.CollectionClusterInfoResponse;
import io.metaloom.qdrant.client.http.model.GenericBooleanStatusResponse;
import io.metaloom.qdrant.client.http.model.cluster.ClusterStatusResponse;
import io.metaloom.qdrant.client.http.model.cluster.CollectionUpdateClusterSetupRequest;

public interface ClusterMethods {

	/**
	 * Get information about the current state and composition of the cluster.
	 * 
	 * @return
	 */
	QDrantClientRequest<ClusterStatusResponse> getClusterStatusInfo();

	/**
	 * Tries to remove peer from the cluster. Will return an error if peer has shards on it.
	 * 
	 * @param peerId
	 * @param force
	 * @return
	 */
	QDrantClientRequest<GenericBooleanStatusResponse> removePeerFromCluster(String peerId, boolean force);

	/**
	 * Get cluster information for a collection.
	 * 
	 * @param collectionName
	 * @return
	 */
	QDrantClientRequest<CollectionClusterInfoResponse> getCollectionClusterInfo(String collectionName);

	/**
	 * Update collection cluster setup.
	 * 
	 * @param collectionName
	 * @param request
	 * @return
	 */
	QDrantClientRequest<GenericBooleanStatusResponse> updateCollectionClusterSetup(String collectionName,
		CollectionUpdateClusterSetupRequest request);

}
