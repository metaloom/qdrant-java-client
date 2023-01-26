package io.metaloom.qdrant.container;

import java.time.Duration;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class QDrantContainer extends GenericContainer<QDrantContainer> {

	public static final String DEFAULT_VERSION = "v0.11.7";

	public static final int HTTP_PORT = 6333;

	public static final int GRCP_PORT = 6334;

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

		withExposedPorts(HTTP_PORT, GRCP_PORT);
		withStartupTimeout(Duration.ofSeconds(15L));
		waitingFor(Wait.forHttp("/").forPort(HTTP_PORT));

	}

	public int grcpPort() {
		return getMappedPort(GRCP_PORT);
	}

	public int httpPort() {
		return getMappedPort(HTTP_PORT);
	}
}
