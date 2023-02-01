package io.metaloom.qdrant.client.testcases;

public interface CollectionClientTestcases {

	void testCreateCollectionWithNamedVectorParams() throws Exception;

	void testCreateCollectionWithUnnamedVectorParams() throws Exception;

	void testListCollections() throws Exception;

	void testGetCollectionInfo() throws Exception;

	void testDeleteCollection() throws Exception;

}
