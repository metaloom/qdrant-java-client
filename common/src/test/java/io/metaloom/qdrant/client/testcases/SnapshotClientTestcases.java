package io.metaloom.qdrant.client.testcases;

public interface SnapshotClientTestcases {

	void testCreateCollectionSnapshot() throws Exception;

	void testListCollectionSnapshot() throws Exception;

	void testDownloadCollectionSnapshot() throws Exception;

	void testRecoverCollectionSnapshot() throws Exception;

	void testCreateStorageSnapshot() throws Exception;

	void testListStorageSnapshot() throws Exception;

	void testDownloadStorageSnapshot() throws Exception;

	void testRecoverStorageSnapshot() throws Exception;
}
