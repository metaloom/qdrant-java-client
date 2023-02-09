package io.metaloom.qdrant.client.http;

import com.fasterxml.jackson.databind.JsonNode;

import io.metaloom.qdrant.client.http.impl.HttpErrorException;
import io.metaloom.qdrant.client.http.impl.QDrantClientRequestImpl;
import io.metaloom.qdrant.client.http.model.RestRequestModel;
import io.metaloom.qdrant.client.http.model.RestResponse;
import io.metaloom.qdrant.client.http.model.query.ReadConsistencyType;
import io.metaloom.qdrant.client.http.model.query.WriteOrdering;
import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;

public interface QDrantClientRequest<T extends RestResponse> {

	public static final String PUT = "PUT";
	public static final String PATCH = "PATCH";
	public static final String GET = "GET";
	public static final String DELETE = "DELETE";
	public static final String POST = "POST";

	/**
	 * Create request without payload.
	 * 
	 * @param <T>
	 * @param method
	 * @param path
	 * @param qdrantClient
	 * @param okClient
	 * @param responseClass
	 * @return
	 */
	public static <T extends RestResponse> QDrantClientRequest<T> create(String method, String path, QDrantHttpClient qdrantClient,
		OkHttpClient okClient, Class<T> responseClass) {
		return new QDrantClientRequestImpl<>(method, path, qdrantClient, okClient, null, responseClass);
	}

	/**
	 * Create request with payload.
	 * 
	 * @param <T>
	 * @param method
	 * @param path
	 * @param qdrantClient
	 * @param okClient
	 * @param request
	 * @param responseClass
	 * @return
	 */
	public static <T extends RestResponse> QDrantClientRequest<T> create(String method, String path, QDrantHttpClient qdrantClient,
		OkHttpClient okClient, RestRequestModel request, Class<T> responseClass) {
		return new QDrantClientRequestImpl<>(method, path, qdrantClient, okClient, request, responseClass);
	}

	/**
	 * Add an additional query parameter.
	 * 
	 * @param key
	 * @param value
	 * @return Fluent API
	 */
	QDrantClientRequest<T> addQueryParameter(String key, String value);

	/**
	 * Add the wait query parameter.
	 * 
	 * @param wait
	 * @return
	 */
	QDrantClientRequest<T> addWait(boolean wait);

	/**
	 * Add the timeout query parameter to the request.
	 * 
	 * @param timeout
	 * @return
	 */
	QDrantClientRequest<T> addTimeout(int timeout);

	/**
	 * Add the anonymize query parameter to the request.
	 * 
	 * @param anonymize
	 * @return
	 */
	QDrantClientRequest<T> addAnonymize(boolean anonymize);

	/**
	 * Define read consistency guarantees for the operation.
	 * 
	 * @param type
	 * @return
	 */
	QDrantClientRequest<T> addConsistency(ReadConsistencyType type);

	/**
	 * Define read consistency guarantees for the operation.
	 * 
	 * @param nRequests
	 * @return
	 */
	QDrantClientRequest<T> addConsistency(int nRequests);

	/**
	 * Define ordering guarantees for the operation.
	 *
	 * @param ordering
	 * @return
	 */
	QDrantClientRequest<T> addWriteOrdering(WriteOrdering ordering);

	/**
	 * Add the force query parameter to the request.
	 * 
	 * @param force
	 * @return
	 */
	QDrantClientRequest<T> addForce(boolean force);

	/**
	 * Returns a single which can be used to execute the request and listen to the result.
	 * 
	 * @return
	 */
	Single<T> async();

	/**
	 * Executes the request in a synchronized blocking way and returns the returned JSON data.
	 * 
	 * @return
	 * @throws HttpErrorException
	 */
	JsonNode json() throws HttpErrorException;

	/**
	 * Executes the request in a synchronized blocking way.
	 * 
	 * @return
	 * @throws HttpErrorException
	 */
	T sync() throws HttpErrorException;

}
