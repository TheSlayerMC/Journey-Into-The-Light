package net.jitl.util;

import java.util.Objects;
import java.util.function.Function;

/**
 * Used for creating a result using two arguments
 *
 * @param <A> The first argument
 * @param <B> The second argument
 * @param <C> The third argument
 * @param <R> The result
 */
@FunctionalInterface
public interface TriFunction<A, B, C, R> {

	/**
	 * Applies the function with the given arguments
	 *
	 * @param a The first argument
	 * @param b The second argument
	 * @param c The third argument
	 * @return The result
	 */
	R apply(A a, B b, C c);

	/**
	 * >>>>(Copy of {@link java.util.function.BiFunction} documentation because it's basically the exact same)<<<<
	 * <p>
	 * Returns a composed function that first applies this function to
	 * its input, and then applies the {@code after} function to the result.
	 * If evaluation of either function throws an exception, it is relayed to
	 * the caller of the composed function.
	 *
	 * @param <V>   the type of output of the {@code after} function, and of the
	 *              composed function
	 * @param after the function to apply after this function is applied
	 * @return a composed function that first applies this function and then
	 * applies the {@code after} function
	 * @throws NullPointerException if after is null
	 */
	default <V> TriFunction<A, B, C, V> andThen(
			Function<? super R, ? extends V> after) {
		Objects.requireNonNull(after);
		return (A a, B b, C c) -> after.apply(apply(a, b, c));
	}
}
