package io.metaloom.qdrant.client.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.metaloom.qdrant.client.http.model.cluster.ReplicaState;
import io.metaloom.qdrant.client.http.model.request.CollectionUpdateClusterSetupRequest;
import io.metaloom.qdrant.client.http.model.response.ClusterStatusResponse;
import io.metaloom.qdrant.client.http.model.response.CollectionClusterInfoResponse;
import io.metaloom.qdrant.json.Json;

public class ClusterModelTest {

	@Test
	public void testClusterStatusResponseModel() {
		String json = """
			{
				"time": 0,
				"status": "ok",
				"result": {
				"status": "disabled"
				}
			}
			""";

		ClusterStatusResponse status = Json.parse(json, ClusterStatusResponse.class);
		assertEquals(0f, status.getTime(), 0f);
		assertEquals("ok", status.getStatus());
		assertEquals("disabled", status.getResult().getStatus());
	}

	@Test
	public void testUpdateCollectionClusterSetupRequest() {
		String json = """
			{
				"move_shard": {
					"shard_id": 42,
					"to_peer_id": 43,
					"from_peer_id": 44
				}
			}
			""";

		CollectionUpdateClusterSetupRequest request = Json.parse(json, CollectionUpdateClusterSetupRequest.class);
		assertEquals(42, request.getMoveShardOperation().getShardId());
	}

	@Test
	public void testClusterInfoResponseModel() {
		String json = """
			{
			  "time": 0,
			  "status": "ok",
			  "result": {
			    "peer_id": 1,
			    "shard_count": 2,
			    "local_shards": [
			      {
			        "shard_id": 0,
			        "points_count": 0,
			        "state": "Active"
			      }
			    ],
			    "remote_shards": [
			      {
			        "shard_id": 0,
			        "peer_id": 0,
			        "state": "Active"
			      }
			    ],
			    "shard_transfers": [
			      {
			        "shard_id": 0,
			        "from": 0,
			        "to": 0,
			        "sync": true
			      }
			    ]
			  }
			}
			""";

		CollectionClusterInfoResponse info = Json.parse(json, CollectionClusterInfoResponse.class);
		assertEquals(1, info.getResult().getPeerId());
		assertEquals(2, info.getResult().getShardCount());
		assertEquals(0, info.getResult().getLocalShards().get(0).getShardId());
		assertEquals(ReplicaState.ACTIVE, info.getResult().getLocalShards().get(0).getState());
		assertEquals(ReplicaState.ACTIVE, info.getResult().getRemoteShards().get(0).getState());
		assertTrue(info.getResult().getShardTransfers().get(0).isSync());
	}
}
