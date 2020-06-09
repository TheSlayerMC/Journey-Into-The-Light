package net.journey.util;

import java.util.function.Consumer;

public class Initializer<T> {
	private final T obj;

	public Initializer(T obj) {
		this.obj = obj;
	}

	public T apply(Consumer<T> setting) {
		setting.accept(obj);

		return obj;
	}

	public T get() {
		return obj;
	}

	public static <T> Initializer<T> of(T obj) {
		return new Initializer<>(obj);
	}
}
