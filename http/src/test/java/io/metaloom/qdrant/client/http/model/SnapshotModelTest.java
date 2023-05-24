package io.metaloom.qdrant.client.http.model;

import static io.metaloom.qdrant.client.http.model.snapshot.SnapshotPriority.SNAPSHOT;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import io.metaloom.qdrant.client.http.model.snapshot.SnapshotListResponse;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotRecoverRequest;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotResponse;

public class SnapshotModelTest extends AbstractModelTest {

	@Test
	public void testSnapshotRecoverRequestModel() {
		SnapshotRecoverRequest request = load("snapshot/snapshot-recover-request", SnapshotRecoverRequest.class);
		assertEquals("http://example.com", request.getLocation());
		assertEquals(SNAPSHOT, request.getPriority());
	}

	@Test
	public void testSnapshotListResponseModel() {
		SnapshotListResponse response = load("snapshot/snapshot-list-response", SnapshotListResponse.class);
		assertEquals("testName", response.getResult().get(0).getName());
	}

	@Test
	public void testSnapshotResponseModel() {
		SnapshotResponse response = load("snapshot/snapshot-response", SnapshotResponse.class);
		assertEquals("testName", response.getResult().getName());
	}
}
