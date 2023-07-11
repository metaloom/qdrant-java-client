package io.metaloom.qdrant.container;

import java.time.Duration;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

/**
 * Testcontainer implementation for a Qdrant container which can be used in unit tests.
 */
public class QDrantContainer extends GenericContainer<QDrantContainer> {

	public static final String DEFAULT_VERSION = "v1.3.1";

	public static final int HTTP_PORT = 6333;

	public static final int GRPC_PORT = 6334;

	public QDrantContainer() {
		super("qdrant/qdrant:" + DEFAULT_VERSION);
	}

	public QDrantContainer(String version) {
		super("qdrant/qdrant:" + version);
	}

	@Override
	protected void configure() {
		withLogConsumer(c -> {
			System.out.print(c.getUtf8String());
		});

		// withEnv("QDRANT__CLUSTER__ENABLED", "true");
		withExposedPorts(HTTP_PORT, GRPC_PORT);
		withStartupTimeout(Duration.ofSeconds(15L));
		waitingFor(Wait.forHttp("/").forPort(HTTP_PORT));
	}

	public int grpcPort() {
		return getMappedPort(GRPC_PORT);
	}

	public int httpPort() {
		return getMappedPort(HTTP_PORT);
	}
}
