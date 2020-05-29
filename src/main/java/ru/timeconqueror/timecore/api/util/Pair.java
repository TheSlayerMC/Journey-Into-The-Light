package ru.timeconqueror.timecore.api.util;

public class Pair<A, B> {
	private A a;
	private B b;

	public Pair(A a, B b) {
		this.a = a;
		this.b = b;
	}

	public static <A, B> Pair<A, B> of(A a, B b) {
		return new Pair<>(a, b);
	}

	public A getA() {
		return this.a;
	}

	public B getB() {
		return this.b;
	}

	@Override
	public String toString() {
		return "{" + a + " -> " + b + "}";
	}
}