package io.metaloom.qdrant.client;

import org.junit.Rule;

import io.metaloom.qdrant.container.QDrantContainer;

public abstract class AbstractContainerTest {

	@Rule
	public QDrantContainer qdrant = new QDrantContainer();

	protected void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
