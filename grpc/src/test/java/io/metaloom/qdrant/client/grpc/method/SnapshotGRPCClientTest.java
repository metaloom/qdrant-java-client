package io.metaloom.qdrant.client.grpc.method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import io.metaloom.qdrant.client.grpc.AbstractGRPCClientTest;
import io.metaloom.qdrant.client.grpc.proto.SnapshotsService.CreateSnapshotResponse;
import io.metaloom.qdrant.client.grpc.proto.SnapshotsService.ListSnapshotsResponse;
import io.metaloom.qdrant.client.grpc.proto.SnapshotsService.SnapshotDescription;
import io.metaloom.qdrant.client.testcases.SnapshotClientTestcases;

public class SnapshotGRPCClientTest extends AbstractGRPCClientTest implements SnapshotClientTestcases {

	@Test
	@Override
	public void testCreateCollectionSnapshot() throws Exception {
		createCollection(TEST_COLLECTION_NAME);
		client.createSnapshot(TEST_COLLECTION_NAME).sync();
		assertEquals(1, client.listSnapshots(TEST_COLLECTION_NAME).sync().getSnapshotDescriptionsCount());
		client.createSnapshot(TEST_COLLECTION_NAME).sync();
		assertEquals(2, client.listSnapshots(TEST_COLLECTION_NAME).sync().getSnapshotDescriptionsCount());
	}

	@Test
	@Override
	public void testListCollectionSnapshot() throws Exception {
		createCollection(TEST_COLLECTION_NAME);
		client.createSnapshot(TEST_COLLECTION_NAME).sync();
		ListSnapshotsResponse response = client.listSnapshots(TEST_COLLECTION_NAME).sync();
		List<SnapshotDescription> list = response.getSnapshotDescriptionsList();
		assertEquals(1, list.size());
		assertNotNull(list.get(0).getName());
	}

	@Test
	@Override
	@Ignore("Not supported for gRPC")
	public void testDownloadCollectionSnapshot() throws Exception {

	}

	@Test
	@Override
	@Ignore("Not supported for gRPC")
	public void testRecoverCollectionSnapshot() throws Exception {

	}

	@Test
	@Override
	public void testCreateStorageSnapshot() throws Exception {
		CreateSnapshotResponse response = client.createSnapshot().sync();
		assertTrue(response.getSnapshotDescription().getName().startsWith("full-snapshot-"));

		List<SnapshotDescription> list = client.listSnapshots().sync().getSnapshotDescriptionsList();
		assertEquals(1, list.size());
	}

	@Test
	@Override
	public void testListStorageSnapshot() throws Exception {
		client.createSnapshot().sync();
		ListSnapshotsResponse response = client.listSnapshots().sync();
		List<SnapshotDescription> list = response.getSnapshotDescriptionsList();
		assertEquals(1, list.size());
		assertNotNull(list.get(0).getName());

	}

	@Test
	@Override
	@Ignore("Not supported for gRPC")
	public void testDownloadStorageSnapshot() throws Exception {
		// TODO Auto-generated method stub

	}

	@Test
	@Override
	@Ignore("Not supported for gRPC")
	public void testRecoverStorageSnapshot() throws Exception {
		// TODO Auto-generated method stub

	}

}
