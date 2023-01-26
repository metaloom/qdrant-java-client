package io.metaloom.qdrant.client.http.impl;

import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

import io.reactivex.rxjava3.core.Single;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;

public class RequestBuilder {

	public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

	public static final MediaType MEDIA_TYPE_NDJSON = MediaType.parse("application/x-ndjson");

	private final QDrantHttpClientImpl client;

	private final okhttp3.HttpUrl.Builder urlBuilder;

	private RequestBody body;

	private final String method;

	public RequestBuilder(String method, String path, QDrantHttpClientImpl client, JSONObject... json) {
		this.client = client;
		this.method = method;
		this.urlBuilder = createUrlBuilder(path);

		RequestBody body = null;
		if (json != null && json.length == 1) {
			body = RequestBody.create(json[0].toString(), MEDIA_TYPE_JSON);
		}
		// Build a NDJSON if more than one object has been provided
		if (json != null && json.length > 1) {
			StringBuilder builder = new StringBuilder();
			for (JSONObject element : json) {
				builder.append(element.toString());
				builder.append("\n");
			}
			body = RequestBody.create(builder.toString().getBytes(StandardCharsets.UTF_8), MEDIA_TYPE_NDJSON);
		}
		this.body = body;
	}

	public RequestBuilder(String method, String path, QDrantHttpClientImpl client, String bulkData) {
		this.client = client;
		this.method = method;
		this.urlBuilder = createUrlBuilder(path);
		this.body = RequestBody.create(bulkData.getBytes(StandardCharsets.UTF_8), MEDIA_TYPE_NDJSON);
	}

	private okhttp3.HttpUrl.Builder createUrlBuilder(String path) {
		return new HttpUrl.Builder()
			.scheme(client.getScheme())
			.host(client.getHostname())
			.port(client.getPort())
			.addPathSegments(path);
	}

	private Request build() {
		Builder builder = new Request.Builder().url(urlBuilder.build());
		builder.method(method, body);
		return builder.build();
	}

	/**
	 * Executes the request in a synchronized blocking way.
	 * 
	 * @return
	 * @throws HttpErrorException
	 */
	public JSONObject sync() throws HttpErrorException {
		return client.executeSync(build());
	}

	/**
	 * Returns a single which can be used to execute the request and listen to the result.
	 * 
	 * @return
	 */
	public Single<JSONObject> async() {
		return client.executeAsync(build());
	}

	/**
	 * Add an additional query parameter.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public RequestBuilder addQueryParameter(String key, String value) {
		urlBuilder.addQueryParameter(key, value);
		return this;
	}
}
