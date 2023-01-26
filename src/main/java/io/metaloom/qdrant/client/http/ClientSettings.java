package io.metaloom.qdrant.client.http;

import java.time.Duration;
import java.util.function.Supplier;

import com.google.common.util.concurrent.ListenableFuture;

import io.grpc.ManagedChannel;
import io.metaloom.qdrant.client.grcp.methods.GrpcClientRequest;

public interface ClientSettings {

	/**
	 * Return the configured gRCP port for the client connection.
	 * 
	 * @return
	 */
	int getPort();

	/**
	 * Return the configured host for the client connection.
	 * 
	 * @return
	 */
	String getHostname();

	/**
	 * Return the prepared gRPC channel.
	 * 
	 * @return
	 */
	ManagedChannel channel();

	/**
	 * Timeout for server connections.
	 * 
	 * @return
	 */
	Duration getConnectTimeout();

	/**
	 * Timeout for read operations.
	 * 
	 * @return
	 */
	Duration getReadTimeout();

	/**
	 * Timeout for write operations.
	 * 
	 * @return
	 */
	Duration getWriteTimeout();

	default <T> GrpcClientRequest<T> request(Supplier<T> blockingSupplier,
		Supplier<ListenableFuture<T>> asyncSupplier) {
		return new GrpcClientRequest<>(this, blockingSupplier, asyncSupplier);
	}

}
