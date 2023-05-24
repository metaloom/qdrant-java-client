package io.metaloom.qdrant.client.grpc;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.metaloom.qdrant.client.testcases.ClusterClientTestcases;

public class ClusterGRPCClientTest extends AbstractGRPCClientTest implements ClusterClientTestcases {

	@Test
	@Override
	@Disabled("Not supported for gRPC")
	public void testGetClusterStatusInfo() throws Exception {

	}

	@Test
	@Override
	@Disabled("Not supported for gRPC")
	public void testRemovePeerFromCluster() throws Exception {

	}

	@Test
	@Override
	@Disabled("Not supported for gRPC")
	public void testCollectionClusterInfo() throws Exception {

	}

	@Test
	@Override
	@Disabled("Not supported for gRPC")
	public void testUpdateCollectionClusterSetup() throws Exception {

	}

}
