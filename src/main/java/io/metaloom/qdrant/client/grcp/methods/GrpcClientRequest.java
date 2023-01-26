package io.metaloom.qdrant.client.grcp.methods;

import java.util.function.Supplier;

import com.google.common.util.concurrent.ListenableFuture;

import io.metaloom.qdrant.client.QDrantClientUtil;
import io.metaloom.qdrant.client.http.ClientSettings;
import io.reactivex.rxjava3.core.Maybe;

public class GrpcClientRequest<T> {

	private final Supplier<T> blocking;
	private final Supplier<ListenableFuture<T>> async;
	private final ClientSettings settings;

	public GrpcClientRequest(ClientSettings settings, Supplier<T> blockingSupplier, Supplier<ListenableFuture<T>> asyncSupplier) {
		this.settings= settings;
		this.blocking = blockingSupplier;
		this.async = asyncSupplier;
	}

	public T blocking() {
		return blocking.get();
	}

	public ListenableFuture<T> future() {
		return async.get();
	}

	public Maybe<T> rx() {
		return QDrantClientUtil.toMaybe(future(), settings);
	}
}
