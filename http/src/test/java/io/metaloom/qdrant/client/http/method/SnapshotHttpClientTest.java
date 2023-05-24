package io.metaloom.qdrant.client.http.method;

import static io.metaloom.qdrant.client.http.test.QDrantHttpClientAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.metaloom.qdrant.client.http.AbstractHTTPClientTest;
import io.metaloom.qdrant.client.http.QDrantBinaryResponse;
import io.metaloom.qdrant.client.http.impl.HttpErrorException;
import io.metaloom.qdrant.client.http.model.collection.CollectionCreateRequest;
import io.metaloom.qdrant.client.http.model.collection.config.Distance;
import io.metaloom.qdrant.client.http.model.collection.config.VectorParams;
import io.metaloom.qdrant.client.http.model.point.PointStruct;
import io.metaloom.qdrant.client.http.model.point.PointsListUpsertRequest;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotDescription;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotRecoverRequest;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotResponse;
import io.metaloom.qdrant.client.testcases.SnapshotClientTestcases;

public class SnapshotHttpClientTest extends AbstractHTTPClientTest implements SnapshotClientTestcases {

	@Test
	@Override
	public void testDownloadCollectionSnapshot() throws Exception {
		// 1. Create Collection
		CollectionCreateRequest request = new CollectionCreateRequest();
		request.setVectors(VectorParams.of(4, Distance.EUCLID));
		assertSuccess(client.createCollection("test", request).sync());

		// 2. Create Snapshot
		SnapshotDescription snapshot = client.createCollectionSnapshot("test").sync().getResult();
		assertNotNull(snapshot.getName());

		// 3. Download snapshot
		Path target = Paths.get("target/test-snapshot.bin");
		if (Files.exists(target)) {
			Files.delete(target);
		}
		try (QDrantBinaryResponse binaryResponse = client.downloadCollectionSnapshot("test", snapshot.getName()).sync()) {
			assertTrue("The request failed. We got response code " + binaryResponse.code(), binaryResponse.isSuccessful());
			try (InputStream ins = binaryResponse.getStream()) {
				Files.copy(ins, target, StandardCopyOption.REPLACE_EXISTING);
			}
			assertTrue(Files.exists(target));
			System.out.println("Binary: " + binaryResponse.getFilename());
		}

	}

	@Test
	@Override
	public void testDeleteCollectionSnapshot() throws Exception {
		createCollection(TEST_COLLECTION_NAME);
		SnapshotDescription response = createCollectionSnapshot(TEST_COLLECTION_NAME);
		assertEquals("There should be one snapshots", 1, invoke(client.listCollectionSnapshots(TEST_COLLECTION_NAME)).getResult().size());
		String snapshotName = response.getName();
		assertSuccess(client.deleteCollectionSnapshot(TEST_COLLECTION_NAME, snapshotName).sync());
		assertEquals("There should be no snapshots", 0, invoke(client.listCollectionSnapshots(TEST_COLLECTION_NAME)).getResult().size());
	}

	@Test
	@Override
	public void testCreateCollectionSnapshot() throws Exception {
		createCollection(TEST_COLLECTION_NAME);
		SnapshotDescription response = createCollectionSnapshot(TEST_COLLECTION_NAME);
		assertNotNull(response);
		assertTrue("The name did not match. Got: " + response.getName(), response.getName().startsWith(TEST_COLLECTION_NAME));
	}

	@Test
	@Override
	public void testListCollectionSnapshot() throws Exception {
		createCollection(TEST_COLLECTION_NAME);
		createCollectionSnapshot(TEST_COLLECTION_NAME);
		insertTestPoints(TEST_COLLECTION_NAME, 10);
		// The snapshot filename is named via seconds. We need to pass some time to allow for creation of another file
		sleep(2000);
		createCollectionSnapshot(TEST_COLLECTION_NAME);

		List<SnapshotDescription> list = invoke(client.listCollectionSnapshots(TEST_COLLECTION_NAME)).getResult();
		assertEquals("There should be two snapshots", 2, list.size());
	}

	@Test
	@Override
	public void testRecoverCollectionSnapshot() throws Exception {
		createCollection(TEST_COLLECTION_NAME);
		SnapshotDescription snapshot = createCollectionSnapshot(TEST_COLLECTION_NAME);
		insertTestPoints(TEST_COLLECTION_NAME, 10);
		assertThat(client).hasPoints(TEST_COLLECTION_NAME, 10);
		// Invoke restore and assert that all points are gone
		SnapshotRecoverRequest request = new SnapshotRecoverRequest();
		request.setLocation("file:///qdrant/snapshots/" + TEST_COLLECTION_NAME + "/" + snapshot.getName());
		invoke(client.recoverSnapshot(TEST_COLLECTION_NAME, request));
		assertThat(client).hasPoints(TEST_COLLECTION_NAME, 0);
	}

	@Test
	@Override
	public void testCreateStorageSnapshot() throws Exception {
		SnapshotDescription snapshot = invoke(client.createStorageSnapshot()).getResult();
		assertNotNull(snapshot);
		assertTrue("The expected name was not correct. Got: " + snapshot.getName(), snapshot.getName().startsWith("full-snapshot-"));
	}

	@Test
	@Override
	public void testListStorageSnapshot() throws Exception {
		invoke(client.createStorageSnapshot());
		createCollection(TEST_COLLECTION_NAME);
		// The snapshot filename is named via seconds. We need to pass some time to allow for creation of another file
		sleep(2000);
		invoke(client.createStorageSnapshot());
		List<SnapshotDescription> snapshots = invoke(client.listStorageSnapshots()).getResult();
		assertEquals(2, snapshots.size());
	}

	@Test
	@Override
	public void testDownloadStorageSnapshot() throws Exception {
		SnapshotDescription snapshot = invoke(client.createStorageSnapshot()).getResult();

		Path target = Paths.get("target/test-storage-snapshot.bin");
		if (Files.exists(target)) {
			Files.delete(target);
		}
		try (QDrantBinaryResponse binaryResponse = client.downloadStorageSnapshot(snapshot.getName()).sync()) {
			assertTrue("The request failed. We got response code " + binaryResponse.code(), binaryResponse.isSuccessful());
			try (InputStream ins = binaryResponse.getStream()) {
				Files.copy(ins, target, StandardCopyOption.REPLACE_EXISTING);
			}
			assertTrue(Files.exists(target));
			System.out.println("Binary: " + binaryResponse.getFilename());
		}
	}

	@Test
	@Override
	@Disabled("It is unclear how to recover from a storage snapshot. No endpoint defined.")
	public void testRecoverStorageSnapshot() throws Exception {

	}

	private void createCollection(String collectionName) throws HttpErrorException {
		CollectionCreateRequest request = new CollectionCreateRequest();
		request.setVectors(VectorParams.of(4, Distance.EUCLID));
		invoke(client.createCollection(collectionName, request));
	}

	private SnapshotDescription createCollectionSnapshot(String collectionName) throws HttpErrorException {
		SnapshotResponse response = invoke(client.createCollectionSnapshot(collectionName));
		assertNotNull(response.getResult().getName());
		return response.getResult();
	}

	private void insertTestPoints(String testCollectionName, int count) throws Exception {
		PointsListUpsertRequest pointsRequest = new PointsListUpsertRequest();
		for (int i = 0; i < count; i++) {
			PointStruct point = PointStruct.of(VECTOR_1).setId(1337 + i);
			point.setPayload("{\"name\": \"first\"}");
			pointsRequest.addPoint(point);
		}
		invoke(client.upsertPoints(TEST_COLLECTION_NAME, pointsRequest, true));
	}

}
