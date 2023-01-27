package io.metaloom.qdrant.client.http.method;

import io.metaloom.qdrant.client.http.HTTPMethods;

public interface LockMethods extends HTTPMethods {

	void lock();
	
	void lockStatus();

}
