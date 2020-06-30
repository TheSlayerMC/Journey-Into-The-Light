package ru.timeconqueror.timecore.api.util;

public class MathUtils {
	/**
	 * Coerces number in provided range.
	 *
	 * @param number number to coerce
	 * @param min    minimum value, inclusive.
	 * @param max    maximum value, inclusive.
	 */
	public static int coerceInRange(int number, int min, int max) {
		return Math.min(Math.max(number, min), max);
	}

	/**
	 * Coerces number in provided range.
	 *
	 * @param number number to coerce
	 * @param min    minimum value, inclusive.
	 * @param max    maximum value, inclusive.
	 */
	public static float coerceInRange(float number, float min, float max) {
		return Math.min(Math.max(number, min), max);
	}

	/**
	 * Coerces number in provided range.
	 *
	 * @param number number to coerce
	 * @param min    minimum value, inclusive.
	 * @param max    maximum value, inclusive.
	 */
	public static double coerceInRange(double number, double min, double max) {
		return Math.min(Math.max(number, min), max);
	}

	/**
	 * Calculates the value, that represents the part ({@code percentage}) of the {@code [start-to-end]} range, counting from the {@code start}.
	 * Also works if {@code end} is less then {@code start}
	 *
	 * @param factor percentage value
	 * @param start  start point, can be also more than end
	 * @param end    end point, can be also less than end
	 */
	public static float interpolate(float factor, float start, float end) {
		return start + factor * (end - start);
	}

	/**
	 * Calculates the value, that represents the part ({@code percentage}) of the {@code [start-to-end]} range, counting from the {@code start}.
	 * Also works if {@code end} is less then {@code start}
	 *
	 * @param factor percentage value
	 * @param start  start point, can be also more than end
	 * @param end    end point, can be also less than end
	 */
	public static double interpolate(double factor, double start, double end) {
		return start + factor * (end - start);
	}

	/**
	 * Returns the percentage value (from 0 to 1), which represents, what percentage of the {@code [start-to-end]} range {@code current} number takes.
	 *
	 * @param current number, that is in {@code [start-to-end]} range.
	 * @param start   start value (0)
	 * @param end     end value (1)
	 */
	public static float calcPercentage(float current, float start, float end) {
		return end - start != 0 ? (current - start) / (end - start) : 1;
	}

	/**
	 * Returns the percentage value (from 0 to 1), which represents, what percentage of the {@code [start-to-end]} range {@code current} number takes.
	 *
	 * @param current number, that is in {@code [start-to-end]} range.
	 * @param start   start value (0)
	 * @param end     end value (1)
	 */
	public static double calcPercentage(double current, double start, double end) {
		return end - start != 0 ? (current - start) / (end - start) : 1;
	}

	public static float lerp(float pct, float start, float end) {
		return start + pct * (end - start);
	}

	public static double lerp(double pct, double start, double end) {
		return start + pct * (end - start);
	}
}
