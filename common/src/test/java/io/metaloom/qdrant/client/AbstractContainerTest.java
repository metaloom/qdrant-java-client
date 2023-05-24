package io.metaloom.qdrant.client;

import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import io.metaloom.qdrant.container.QDrantContainer;

@Testcontainers
public abstract class AbstractContainerTest {

	@Container
	protected QDrantContainer qdrant = new QDrantContainer();

	protected void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
