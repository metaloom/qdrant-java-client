package io.metaloom.qdrant.client.http.method;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.junit.Test;

import io.metaloom.qdrant.client.http.AbstractClientTest;
import io.metaloom.qdrant.client.http.QDrantBinaryResponse;
import io.metaloom.qdrant.client.http.impl.HttpErrorException;
import io.metaloom.qdrant.client.http.model.collection.CollectionCreateRequest;
import io.metaloom.qdrant.client.http.model.collection.config.Distance;
import io.metaloom.qdrant.client.http.model.collection.config.VectorsConfig;
import io.metaloom.qdrant.client.http.model.snapshot.SnapshotDescription;

public class SnapshotMethodTest extends AbstractClientTest {

	@Test
	public void testDownloadSnapshot() throws HttpErrorException, IOException, InterruptedException {
		// 1. Create Collection
		CollectionCreateRequest request = new CollectionCreateRequest();
		request.setVectors(new VectorsConfig().setSize(4).setDistance(Distance.EUCLID));
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

}
