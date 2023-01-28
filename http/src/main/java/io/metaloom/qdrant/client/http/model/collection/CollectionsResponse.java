package io.metaloom.qdrant.client.http.model.collection;

import java.util.List;

import io.metaloom.qdrant.client.http.model.RestModel;

public class CollectionsResponse implements RestModel {

	private List<CollectionDescription> collections;

	public List<CollectionDescription> getCollections() {
		return collections;
	}

	public CollectionsResponse setCollections(List<CollectionDescription> collections) {
		this.collections = collections;
		return this;
	}
}
