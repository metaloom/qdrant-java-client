package io.metaloom.qdrant.client;

import org.junit.Rule;

import io.metaloom.qdrant.container.QDrantContainer;

public abstract class AbstractContainerTest {

	@Rule
	public QDrantContainer qdrant = new QDrantContainer();

}
