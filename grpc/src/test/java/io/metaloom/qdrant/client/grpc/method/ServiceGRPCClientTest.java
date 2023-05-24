package io.metaloom.qdrant.client.grpc.method;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.metaloom.qdrant.client.grpc.AbstractGRPCClientTest;
import io.metaloom.qdrant.client.testcases.ServiceClientTestcases;

public class ServiceGRPCClientTest extends AbstractGRPCClientTest implements ServiceClientTestcases {

	@Test
	@Override
	@Disabled("Not supported for gRPC")
	public void testCollectTelemetryData() throws Exception {

	}

	@Test
	@Override
	@Disabled("Not supported for gRPC")
	public void testLockOptions() throws Exception {

	}

}
