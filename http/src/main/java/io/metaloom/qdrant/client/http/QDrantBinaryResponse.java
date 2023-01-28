package io.metaloom.qdrant.client.http;

import java.io.InputStream;
import java.util.Arrays;

import io.metaloom.qdrant.client.http.model.RestResponse;
import io.reactivex.rxjava3.core.Flowable;

public interface QDrantBinaryResponse extends RestResponse, AutoCloseable {

	int FLOWABLE_BUFFER_SIZE = 8192;

	/**
	 * Retrieve a blocking input stream of the response body. This object must be closed via #close after the stream has been read.
	 * 
	 * @return
	 */
	InputStream getStream();

	/**
	 * Retrieve a Flowable which emits byte chunks. The response is closed when all bytes have been read. It is advised to not use close or the auto closable
	 * manually when working with this Flowable, since the bytes could be emitted asynchronously.
	 * 
	 * @return
	 */
	default Flowable<byte[]> getFlowable() {
		Flowable<byte[]> f = Flowable.defer(() -> {
			InputStream stream = getStream();
			return Flowable.generate(emitter -> {
				byte[] buffer = new byte[FLOWABLE_BUFFER_SIZE];
				int count = stream.read(buffer);
				if (count == -1) {
					stream.close();
					close();
					emitter.onComplete();
				} else if (count < FLOWABLE_BUFFER_SIZE) {
					emitter.onNext(Arrays.copyOf(buffer, count));
				} else {
					emitter.onNext(buffer);
				}
			});
		});

		return f.doFinally(() -> close());
	}

	/**
	 * Retrieve the filename of this binary
	 * 
	 * @return
	 */
	String getFilename();

	/**
	 * Retrieve the content type of the binary
	 * 
	 * @return
	 */
	String getContentType();

	/**
	 * Closes the response body handler.
	 */
	void close();

	/**
	 * Check whether the request was successful.
	 * 
	 * @return
	 */
	boolean isSuccessful();

	/**
	 * Return the HTTP status code for the response.
	 * 
	 * @return
	 */
	int code();
}
