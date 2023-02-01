package io.metaloom.qdrant.client.grpc.method;

import org.junit.Ignore;
import org.junit.Test;

import io.metaloom.qdrant.client.grpc.AbstractGRPCClientTest;
import io.metaloom.qdrant.client.testcases.ServiceClientTestcases;

public class ServiceGRPCClientTest extends AbstractGRPCClientTest implements ServiceClientTestcases {

	@Test
	@Override
	@Ignore("Not supported for gRPC")
	public void testCollectTelemetryData() throws Exception {

	}

	@Test
	@Override
	@Ignore("Not supported for gRPC")
	public void testLockOptions() throws Exception {

	}

}
