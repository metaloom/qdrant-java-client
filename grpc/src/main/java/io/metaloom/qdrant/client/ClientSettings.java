package io.metaloom.qdrant.client;

import java.time.Duration;
import java.util.function.Supplier;

import com.google.common.util.concurrent.ListenableFuture;

import io.grpc.ManagedChannel;
import io.metaloom.qdrant.client.grpc.method.GrpcClientRequest;

public interface ClientSettings extends CommonSettings {

	/**
	 * Return the prepared gRPC channel.
	 * 
	 * @return
	 */
	ManagedChannel channel();

	default <T> GrpcClientRequest<T> request(Supplier<T> blockingSupplier,
		Supplier<ListenableFuture<T>> asyncSupplier) {
		return new GrpcClientRequest<>(this, blockingSupplier, asyncSupplier);
	}

}
