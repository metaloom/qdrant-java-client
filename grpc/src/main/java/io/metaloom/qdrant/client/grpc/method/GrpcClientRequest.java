package io.metaloom.qdrant.client.grpc.method;

import java.util.function.Supplier;

import com.google.common.util.concurrent.ListenableFuture;

import io.metaloom.qdrant.client.ClientSettings;
import io.metaloom.qdrant.client.util.QDrantClientUtil;
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

	public T sync() {
		return blocking.get();
	}

	public ListenableFuture<T> async() {
		return async.get();
	}

	public Maybe<T> rx() {
		return QDrantClientUtil.toMaybe(async(), settings);
	}
}
