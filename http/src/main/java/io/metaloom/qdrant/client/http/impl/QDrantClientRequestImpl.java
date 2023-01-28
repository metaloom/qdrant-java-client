package io.metaloom.qdrant.client.http.impl;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import io.metaloom.qdrant.client.http.QDrantBinaryResponse;
import io.metaloom.qdrant.client.http.QDrantClientRequest;
import io.metaloom.qdrant.client.http.QDrantHttpClient;
import io.metaloom.qdrant.client.http.model.RestModel;
import io.metaloom.qdrant.client.http.model.RestRequestModel;
import io.metaloom.qdrant.client.http.model.RestResponse;
import io.metaloom.qdrant.json.Json;
import io.reactivex.rxjava3.core.Single;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class QDrantClientRequestImpl<T extends RestResponse> implements QDrantClientRequest<T> {

	public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

	private final QDrantHttpClient qdrantClient;

	private final OkHttpClient okClient;

	private final okhttp3.HttpUrl.Builder urlBuilder;

	private final Class<T> responseClass;

	private RequestBody body;

	private final String method;

	public QDrantClientRequestImpl(String method, String path, QDrantHttpClient qdrantClient, OkHttpClient client, RestRequestModel requestModel,
		Class<T> responseClass) {
		this.qdrantClient = qdrantClient;
		this.okClient = client;
		this.method = method;
		this.urlBuilder = createUrlBuilder(path);
		this.responseClass = responseClass;

		if (requestModel != null) {
			this.body = RequestBody.create(Json.parse(requestModel), MEDIA_TYPE_JSON);
		} else {
			if (method.equals("POST")) {
				this.body = RequestBody.create("", null);
			}
		}
	}

	private okhttp3.HttpUrl.Builder createUrlBuilder(String path) {
		return new HttpUrl.Builder()
			.scheme(qdrantClient.getScheme())
			.host(qdrantClient.getHostname())
			.port(qdrantClient.getPort())
			.addPathSegments(path);
	}

	private Request build() {
		Builder builder = new Request.Builder().url(urlBuilder.build());
		builder.method(method, body);
		return builder.build();
	}

	@Override
	public JsonNode json() throws HttpErrorException {
		return executeSyncJson(build());
	}

	@Override
	public Single<T> async() {
		return executeAsync(build());
	}

	@Override
	public QDrantClientRequest<T> addQueryParameter(String key, String value) {
		urlBuilder.addQueryParameter(key, value);
		return this;
	}

	@Override
	public QDrantClientRequest<T> addWait(boolean wait) {
		return addQueryParameter("wait", String.valueOf(wait));
	}

	@Override
	public QDrantClientRequest<T> addAnonymize(boolean anonymize) {
		return addQueryParameter("anonymize", String.valueOf(anonymize));
	}

	@Override
	public QDrantClientRequest<T> addTimeout(int timeout) {
		return addQueryParameter("timeout", String.valueOf(timeout));
	}

	@Override
	public QDrantClientRequest<T> addForce(boolean force) {
		return addQueryParameter("force", String.valueOf(force));
	}

	@Override
	public T sync() throws HttpErrorException {
		return executeSync(build());
	}

	/**
	 * Execute the request synchronously.
	 * 
	 * @param request
	 * @return Response body text
	 * @throws HttpErrorException
	 */
	private String executeSyncPlain(Request request) throws HttpErrorException {
		try (Response response = okClient.newCall(request).execute()) {
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

			return bodyStr;
		} catch (IOException e1) {
			throw new HttpErrorException("Error while excuting request", e1);
		}
	}

	private QDrantBinaryResponse executeSyncBinary(Request request) throws HttpErrorException {
		try {
			Response response = okClient.newCall(request).execute();
			return new QDrantBinaryResponseImpl(response);
		} catch (IOException e1) {
			throw new HttpErrorException("Error while excuting request", e1);
		}
	}

	@SuppressWarnings("unchecked")
	public T executeSync(Request request) throws HttpErrorException {
		if (RestModel.class.isAssignableFrom(responseClass)) {
			Class<? extends RestModel> r = (Class<? extends RestModel>) responseClass;
			return (T) Json.parse(executeSyncPlain(request), r);
		} else if (QDrantBinaryResponse.class.equals(responseClass)) {
			return (T) executeSyncBinary(request);
		} else {
			throw new RuntimeException("Unsupported response class encountered. Got: " + responseClass.getName());
		}
	}

	/**
	 * Execute the request synchronously.
	 * 
	 * @param request
	 * @return Parsed response object
	 * @throws HttpErrorException
	 */
	private JsonNode executeSyncJson(Request request) throws HttpErrorException {
		try {
			String bodyStr = executeSyncPlain(request);
			return Json.toJson(bodyStr);
		} catch (JsonProcessingException e) {
			throw new HttpErrorException("Error while excuting request", e);
		}
	}

	/**
	 * Execute the request asynchronously.
	 * 
	 * @param request
	 * @return Single which yields the response data
	 */
	private Single<T> executeAsync(Request request) {
		return Single.create(sub -> {
			Call call = okClient.newCall(request);
			sub.setCancellable(call::cancel);
			call.enqueue(new Callback() {
				@Override
				public void onFailure(Call call, IOException e) {
					// Don't call the onError twice. Canceling will trigger another error.
					if (!"Canceled".equals(e.getMessage())) {
						sub.onError(e);
					}
				}

				@Override
				public void onResponse(Call call, Response response) {
					if (QDrantBinaryResponse.class.equals(responseClass)) {
						sub.onSuccess((T) new QDrantBinaryResponseImpl(response));
						return;
					}

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
						if (RestModel.class.isAssignableFrom(responseClass)) {
							Class<? extends RestModel> r = (Class<? extends RestModel>) responseClass;
							sub.onSuccess((T) Json.parse(bodyStr, r));
						} else {
							throw new RuntimeException("Unsupported response class encountered. Got: " + responseClass.getName());
						}

					} catch (Exception e) {
						sub.onError(e);
					}
				}
			});
		});
	}

}
