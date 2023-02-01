package io.metaloom.qdrant.client.grpc.method;

import org.junit.Ignore;
import org.junit.Test;

import io.metaloom.qdrant.client.grpc.AbstractGRPCClientTest;
import io.metaloom.qdrant.client.testcases.SnapshotClientTestcases;

public class SnapshotGRPCClientTest extends AbstractGRPCClientTest implements SnapshotClientTestcases {

	@Test
	@Override
	@Ignore("Not supported for gRPC")
	public void testDownloadSnapshot() throws Exception {

	}

}
