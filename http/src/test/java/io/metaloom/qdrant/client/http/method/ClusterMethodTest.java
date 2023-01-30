package io.metaloom.qdrant.client.http.method;

import java.math.BigInteger;

import org.junit.Ignore;
import org.junit.Test;

import io.metaloom.qdrant.client.http.AbstractClientTest;
import io.metaloom.qdrant.client.http.impl.HttpErrorException;
import io.metaloom.qdrant.client.http.model.cluster.CollectionUpdateClusterSetupRequest;

public class ClusterMethodTest extends AbstractClientTest {

	@Test
	public void testGetClusterStatusInfo() throws HttpErrorException {
		invoke(client.getClusterStatusInfo());
	}

	@Test
	@Ignore("Clustering not implemented for test environment")
	public void testRemovePeerFromCluster() throws HttpErrorException {
		createTestCollection();
		BigInteger peerId = invoke(client.getCollectionClusterInfo(TEST_COLLECTION_NAME)).getResult().getPeerId();
		invoke(client.removePeerFromCluster(peerId.toString(), true));
	}

	@Test
	public void testCollectionClusterInfo() throws HttpErrorException {
		createTestCollection();
		invoke(client.getCollectionClusterInfo(TEST_COLLECTION_NAME));
	}

	@Test
	@Ignore("Clustering not implemented for test environment")
	public void testUpdateCollectionClusterSetup() throws HttpErrorException {
		CollectionUpdateClusterSetupRequest request = new CollectionUpdateClusterSetupRequest();
		invoke(client.updateCollectionClusterSetup(TEST_COLLECTION_NAME, request));
	}
}
