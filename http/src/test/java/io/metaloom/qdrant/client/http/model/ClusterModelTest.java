package io.metaloom.qdrant.client.http.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.metaloom.qdrant.client.http.model.cluster.ReplicaState;
import io.metaloom.qdrant.client.http.model.collection.CollectionUpdateClusterSetupRequest;

public class ClusterModelTest extends AbstractModelTest {

	@Test
	public void testClusterStatusResponseModel() {
		ClusterStatusResponse status = load("cluster-status-response", ClusterStatusResponse.class);
		assertEquals(0f, status.getTime(), 0f);
		assertEquals("ok", status.getStatus());
		assertEquals("disabled", status.getResult().getStatus());
	}

	@Test
	public void testUpdateCollectionClusterSetupRequest() {
		CollectionUpdateClusterSetupRequest request = load("collection-cluster-setup-request", CollectionUpdateClusterSetupRequest.class);
		assertEquals(42, request.getMoveShardOperation().getShardId());
	}

	@Test
	public void testCollectionClusterInfoResponseModel() {
		CollectionClusterInfoResponse info = load("cluster-info-response", CollectionClusterInfoResponse.class);
		assertEquals(1, info.getResult().getPeerId());
		assertEquals(2, info.getResult().getShardCount());
		assertEquals(0, info.getResult().getLocalShards().get(0).getShardId());
		assertEquals(ReplicaState.ACTIVE, info.getResult().getLocalShards().get(0).getState());
		assertEquals(ReplicaState.ACTIVE, info.getResult().getRemoteShards().get(0).getState());
		assertTrue(info.getResult().getShardTransfers().get(0).isSync());
	}
}
