package io.metaloom.qdrant.client.http.impl;

import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;

import io.metaloom.qdrant.client.http.AbstractQDrantClient;
import io.metaloom.qdrant.client.http.QDrantHttpClient;
import io.metaloom.qdrant.json.Json;
import io.reactivex.rxjava3.core.Single;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Implementation for the {@link QDrantHttpClient}
 */
public class QDrantHttpClientImpl extends AbstractQDrantClient {

	public static final Logger log = LoggerFactory.getLogger(QDrantHttpClientImpl.class);
	
	public static Builder builder() {
		return new Builder();
	}

	private OkHttpClient client;

	/**
	 * Create a new client with a default timeout of 10s for all timeouts (connect, read and write).
	 * 
	 * @param scheme
	 * @param hostname
	 * @param port
	 * @param connectTimeout
	 * @param readTimeout
	 * @param writeTimeout
	 */
	protected QDrantHttpClientImpl(String scheme, String hostname, int port, Duration connectTimeout, Duration readTimeout, Duration writeTimeout) {
		super(scheme, hostname, port, connectTimeout, readTimeout, writeTimeout);
	}

	public void init() {
		this.client = createClient();
	}

	private OkHttpClient createClient() {
		okhttp3.OkHttpClient.Builder builder = new OkHttpClient.Builder();
		builder.connectTimeout(connectTimeout);
		builder.readTimeout(readTimeout);
		builder.writeTimeout(writeTimeout);

		// Disable gzip
		builder.addInterceptor(chain -> {
			Request request = chain.request();
			Request newRequest;
			try {
				newRequest = request.newBuilder().addHeader("Accept-Encoding", "identity").build();
			} catch (Exception e) {
				log.error("Error while creating new request" , e);
				return chain.proceed(request);
			}
			return chain.proceed(newRequest);
		});
		return builder.build();
	}

	/**
	 * Return the used OK HTTP client.
	 * 
	 * @return
	 */
	public OkHttpClient getOkHttpClient() {
		return client;
	}

	@Override
	public void close() {
		// Not needed for OkClient
	}

	@Override
	public RequestBuilder actionBuilder(String method, String path, JsonNode... json) {
		return new RequestBuilder(method, path, this, json);
	}

	@Override
	public RequestBuilder actionBuilder(String method, String path, String bulkData) {
		return new RequestBuilder(method, path, this, bulkData);
	}

	/**
	 * Execute the request synchronously.
	 * 
	 * @param request
	 * @return Parsed response object
	 * @throws HttpErrorException
	 */
	public JsonNode executeSync(Request request) throws HttpErrorException {
		try (Response response = client.newCall(request).execute()) {
			ResponseBody body = response.body();
			String bodyStr = "";
			if (body != null) {
				try {
					bodyStr = body.string();
				} catch (Exception e) {
					throw new HttpErrorException("Error while reading body", e);
				}
			}
			if (!response.isSuccessful()) {
				throw new HttpErrorException("Request failed {" + response.message() + "}", response.code(), bodyStr);
			}

			return Json.toJson(bodyStr);
		} catch (IOException e1) {
			throw new HttpErrorException("Error while excuting request", e1);
		}
	}

	/**
	 * Execute the request asynchronously.
	 * 
	 * @param request
	 * @return Single which yields the response data
	 */
	public Single<JsonNode> executeAsync(Request request) {
		return Single.create(sub -> {
			Call call = client.newCall(request);
			sub.setCancellable(call::cancel);
			call.enqueue(new Callback() {
				@Override
				public void onFailure(Call call, IOException e) {
					// Don't call the onError twice. Cancelling will trigger another error.
					if (!"Canceled".equals(e.getMessage())) {
						sub.onError(e);
					}
				}

				@Override
				public void onResponse(Call call, Response response) {
					try (ResponseBody responseBody = response.body()) {
						ResponseBody body = response.body();
						String bodyStr = "";
						if (body != null) {
							try {
								bodyStr = body.string();
							} catch (Exception e) {
								sub.onError(new HttpErrorException("Error while reading body", e));
								return;
							}
						}
						if (!response.isSuccessful()) {
							sub.onError(new HttpErrorException("Request failed", response.code(), bodyStr));
							return;
						}
						sub.onSuccess(Json.toJson(bodyStr));
					} catch (Exception e) {
						sub.onError(e);
					}
				}
			});
		});
	}

	public static class Builder {

		private String scheme = "http";
		private String hostname = "localhost";
		private int port = 6333;

		private Duration connectTimeout = Duration.ofMillis(10_000);
		private Duration readTimeout = Duration.ofMillis(10_000);
		private Duration writeTimeout = Duration.ofMillis(10_000);

		/**
		 * Verify the builder and build the client.
		 * 
		 * @return
		 */
		public QDrantHttpClientImpl build() {
			Objects.requireNonNull(scheme, "A protocol scheme has to be specified.");
			Objects.requireNonNull(hostname, "A hostname has to be specified.");

			QDrantHttpClientImpl client = new QDrantHttpClientImpl(scheme, hostname, port,
				connectTimeout, readTimeout, writeTimeout);
			client.init();
			return client;
		}

		/**
		 * Set the protocol scheme to be used for the client (e.g.: http, https).
		 * 
		 * @param scheme
		 * @return Fluent API
		 */
		public Builder setScheme(String scheme) {
			this.scheme = scheme;
			return this;
		}

		/**
		 * Set the hostname for the client.
		 * 
		 * @param hostname
		 * @return Fluent API
		 */
		public Builder setHostname(String hostname) {
			this.hostname = hostname;
			return this;
		}

		/**
		 * Set the port to connect to. (e.g. 6333).
		 * 
		 * @param port
		 * @return Fluent API
		 */
		public Builder setPort(int port) {
			this.port = port;
			return this;
		}

		/**
		 * Set connection timeout.
		 * 
		 * @param connectTimeout
		 * @return Fluent API
		 */
		public Builder setConnectTimeout(Duration connectTimeout) {
			this.connectTimeout = connectTimeout;
			return this;
		}

		/**
		 * Set read timeout for the client.
		 * 
		 * @param readTimeout
		 * @return Fluent API
		 */
		public Builder setReadTimeout(Duration readTimeout) {
			this.readTimeout = readTimeout;
			return this;
		}

		/**
		 * Set write timeout for the client.
		 * 
		 * @param writeTimeout
		 * @return Fluent API
		 */
		public Builder setWriteTimeout(Duration writeTimeout) {
			this.writeTimeout = writeTimeout;
			return this;
		}

	}

}
