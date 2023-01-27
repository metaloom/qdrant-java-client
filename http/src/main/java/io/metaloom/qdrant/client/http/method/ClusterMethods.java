package io.metaloom.qdrant.client.http.method;

import io.metaloom.qdrant.client.http.HTTPMethods;
import io.metaloom.qdrant.client.http.impl.RequestBuilder;
import io.metaloom.qdrant.client.http.model.request.CollectionUpdateClusterSetupRequest;
import io.metaloom.qdrant.client.http.model.response.ClusterStatusResponse;
import io.metaloom.qdrant.client.http.model.response.CollectionClusterInfoResponse;
import io.metaloom.qdrant.client.http.model.response.GenericBooleanStatusResponse;

public interface ClusterMethods extends HTTPMethods {

	default RequestBuilder<ClusterStatusResponse> getClusterStatusInfo() {
		return getBuilder("cluster");
	}

	default RequestBuilder<GenericBooleanStatusResponse> removePeerFromCluster(String peerId, boolean force) {
		RequestBuilder<GenericBooleanStatusResponse> request = getBuilder("cluster/peer/" + peerId);
		return request.addQueryParameter("force", String.valueOf(force));
	}

	default RequestBuilder<CollectionClusterInfoResponse> getCollectionClusterInfo(String collectionName) {
		return getBuilder("collections/" + collectionName + "/cluster");
	}

	default RequestBuilder<GenericBooleanStatusResponse> updateCollectionClusterSetup(String collectionName,
		CollectionUpdateClusterSetupRequest request) {
		return postBuilder("collections/" + collectionName + "/cluster", request);
	}

}
