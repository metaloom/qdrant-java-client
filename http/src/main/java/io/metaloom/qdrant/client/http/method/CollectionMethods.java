package io.metaloom.qdrant.client.http.method;

import io.metaloom.qdrant.client.http.HTTPMethods;
import io.metaloom.qdrant.client.http.impl.RequestBuilder;
import io.metaloom.qdrant.client.http.model.GenericBooleanStatusResponse;
import io.metaloom.qdrant.client.http.model.collection.CollectionCreateIndexFieldRequest;
import io.metaloom.qdrant.client.http.model.collection.CollectionCreateRequest;
import io.metaloom.qdrant.client.http.model.collection.CollectionIndexFieldResponse;
import io.metaloom.qdrant.client.http.model.collection.CollectionListResponse;
import io.metaloom.qdrant.client.http.model.collection.CollectionResponse;
import io.metaloom.qdrant.client.http.model.collection.CollectionUpdateAliasesRequest;
import io.metaloom.qdrant.client.http.model.collection.CollectionUpdateRequest;

/**
 * API methods which are used to interact with collections.
 */
public interface CollectionMethods extends HTTPMethods {

	default RequestBuilder<CollectionListResponse> listCollections() {
		return getBuilder("collections");
	}

	default RequestBuilder<CollectionResponse> loadCollection(String collectionName) {
		return getBuilder("collections/" + collectionName);
	}

	default RequestBuilder<GenericBooleanStatusResponse> createCollection(String collectionName, CollectionCreateRequest request) {
		return putBuilder("collections/" + collectionName, request);
	}

	default RequestBuilder<GenericBooleanStatusResponse> updateCollection(String collectionName, CollectionUpdateRequest request, int timeout) {
		RequestBuilder<GenericBooleanStatusResponse> req = patchBuilder("collections/" + collectionName, request);
		req.addQueryParameter("timeout", String.valueOf(timeout));
		return req;
	}

	default RequestBuilder<GenericBooleanStatusResponse> deleteCollection(String collectionName, int timeout) {
		RequestBuilder<GenericBooleanStatusResponse> req = deleteBuilder("collections/" + collectionName);
		req.addQueryParameter("timeout", String.valueOf(timeout));
		return req;
	}

	default RequestBuilder<GenericBooleanStatusResponse> updateCollectionAliases(String collectionName, CollectionUpdateAliasesRequest request,
		int timeout) {
		RequestBuilder<GenericBooleanStatusResponse> req = postBuilder("collections/aliases", request);
		req.addQueryParameter("timeout", String.valueOf(timeout));
		return req;
	}

	default RequestBuilder<CollectionIndexFieldResponse> createCollectionIndexField(String collectionName, CollectionCreateIndexFieldRequest request,
		boolean wait) {
		RequestBuilder<CollectionIndexFieldResponse> req = putBuilder("collections/" + collectionName + "/index", request);
		req.addQueryParameter("wait", String.valueOf(wait));
		return req;
	}

	default RequestBuilder<CollectionIndexFieldResponse> deleteCollectionIndexField(String collectionName, String fieldName , boolean wait) {
		RequestBuilder<CollectionIndexFieldResponse> req = deleteBuilder("collections/" + collectionName + "/index/" + fieldName);
		req.addQueryParameter("wait", String.valueOf(wait));
		return req;
	}

}
