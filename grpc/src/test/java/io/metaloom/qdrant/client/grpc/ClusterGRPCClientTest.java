package io.metaloom.qdrant.client.grpc;

import org.junit.Ignore;
import org.junit.Test;

import io.metaloom.qdrant.client.testcases.ClusterClientTestcases;

public class ClusterGRPCClientTest extends AbstractGRPCClientTest implements ClusterClientTestcases {

	@Test
	@Override
	@Ignore("Not supported for gRPC")
	public void testGetClusterStatusInfo() throws Exception {

	}

	@Test
	@Override
	@Ignore("Not supported for gRPC")
	public void testRemovePeerFromCluster() throws Exception {

	}

	@Test
	@Override
	@Ignore("Not supported for gRPC")
	public void testCollectionClusterInfo() throws Exception {

	}

	@Test
	@Override
	@Ignore("Not supported for gRPC")
	public void testUpdateCollectionClusterSetup() throws Exception {

	}

}
