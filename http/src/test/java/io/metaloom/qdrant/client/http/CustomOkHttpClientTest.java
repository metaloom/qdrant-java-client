package io.metaloom.qdrant.client.http;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;

import io.metaloom.qdrant.client.AbstractContainerTest;
import io.metaloom.qdrant.client.http.model.collection.CollectionCreateRequest;
import io.metaloom.qdrant.client.http.model.collection.config.Distance;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class CustomOkHttpClientTest extends AbstractContainerTest {

	@Test
	public void testCustomClient() throws Exception {

		int port = qdrant.httpPort();
		String host = qdrant.getHost();

		AtomicReference<HttpUrl> interceptedUrl = new AtomicReference<>();
		OkHttpClient customClient = createCustomOkClient(interceptedUrl);

		try (QDrantHttpClient client = QDrantHttpClient.builder()
				.setHostname(host)
				.setOkHttpClient(customClient)
				.setPort(port)
				.build()) {

			// Create a collection
			CollectionCreateRequest req = new CollectionCreateRequest();
			req.setVectors("colors", 4, Distance.EUCLID);
			client.createCollection("the-collection-name", req)
					.sync();
			assertEquals("The intercetor did not catch the correct url", "/collections/the-collection-name", interceptedUrl.get()
					.encodedPath()
					.toString());
		}
	}

	@Test(expected = RuntimeException.class)
	public void testConflictingBuildParams() {
		try (QDrantHttpClient client = QDrantHttpClient.builder()
				.setHostname("localhost")
				.setOkHttpClient(createCustomOkClient(null))
				.setConnectTimeout(Duration.ofMinutes(1))
				.setPort(123)
				.build()) {
		}
	}

	private OkHttpClient createCustomOkClient(AtomicReference<HttpUrl> interceptedUrl) {
		okhttp3.OkHttpClient.Builder builder = new OkHttpClient.Builder();
		builder.addInterceptor(chain -> {
			Request request = chain.request();
			interceptedUrl.set(request.url());
			return chain.proceed(request);
		});
		return builder.build();
	}
}
