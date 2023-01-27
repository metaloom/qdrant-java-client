package io.metaloom.qdrant.client;

import org.junit.ClassRule;

import io.metaloom.qdrant.container.QDrantContainer;

public abstract class AbstractContainerTest {

	@ClassRule
	public static QDrantContainer qdrant = new QDrantContainer();

}
