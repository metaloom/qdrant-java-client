package io.metaloom.qdrant.client.util;

import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ListenableFuture;

import io.metaloom.qdrant.client.ClientSettings;
import io.reactivex.rxjava3.core.Maybe;

public final class QDrantClientUtil {

	private QDrantClientUtil() {
	}

	public static <T> Maybe<T> toMaybe(ListenableFuture<T> fut, ClientSettings settings) {
		return Maybe.fromFuture(fut, settings.getReadTimeout().toMillis(), TimeUnit.MILLISECONDS);
	}
}
