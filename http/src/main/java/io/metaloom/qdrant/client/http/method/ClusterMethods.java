package io.metaloom.qdrant.client.http.method;

import io.metaloom.qdrant.client.http.QDrantClientRequest;
import io.metaloom.qdrant.client.http.model.CollectionClusterInfoResponse;
import io.metaloom.qdrant.client.http.model.GenericBooleanStatusResponse;
import io.metaloom.qdrant.client.http.model.cluster.ClusterStatusResponse;
import io.metaloom.qdrant.client.http.model.cluster.CollectionUpdateClusterSetupRequest;

public interface ClusterMethods {

	QDrantClientRequest<ClusterStatusResponse> getClusterStatusInfo();

	QDrantClientRequest<GenericBooleanStatusResponse> removePeerFromCluster(String peerId, boolean force);

	QDrantClientRequest<CollectionClusterInfoResponse> getCollectionClusterInfo(String collectionName);

	QDrantClientRequest<GenericBooleanStatusResponse> updateCollectionClusterSetup(String collectionName,
		CollectionUpdateClusterSetupRequest request);

}
