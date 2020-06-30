package ru.timeconqueror.timecore.api.util;

import com.google.common.base.Preconditions;

public class Requirements {
	public static void inRangeInclusive(int number, int min, int max) {
		if (number < min || number > max)
			throw new IllegalArgumentException("Number should be in range [" + min + ", " + max + "] (inclusive). Provided: " + number);
	}

	public static void inRangeInclusive(float number, float min, float max) {
		if (number < min || number > max)
			throw new IllegalArgumentException("Number should be in range [" + min + ", " + max + "] (inclusive). Provided: " + number);
	}

	public static void inRangeExclusive(int number, int min, int max) {
		if (number <= min || number >= max)
			throw new IllegalArgumentException("Number should be in range [" + min + ", " + max + "] (exclusive). Provided: " + number);
	}

	public static void inRangeExclusive(float number, float min, float max) {
		if (number <= min || number >= max)
			throw new IllegalArgumentException("Number should be in range [" + min + ", " + max + "] (exclusive). Provided: " + number);
	}

	public static void greaterOrEqualsThan(int number, int min) {
		if (number < min)
			throw new IllegalArgumentException("Provided number should be greater or equal " + min + ". Provided: " + number);
	}

	public static void greaterOrEqualsThan(float number, float min) {
		if (number < min)
			throw new IllegalArgumentException("Provided number should be greater or equal " + min + ". Provided: " + number);
	}

	public static void greaterThan(int number, int min) {
		if (number <= min)
			throw new IllegalArgumentException("Provided number should be greater than " + min + ". Provided: " + number);
	}

	public static void greaterThan(float number, float min) {
		if (number <= min)
			throw new IllegalArgumentException("Provided number should be greater than " + min + ". Provided: " + number);
	}

	public static void lessThan(int number, int max) {
		if (number >= max)
			throw new IllegalArgumentException("Provided number should be less than " + max + ". Provided: " + number);
	}

	public static void lessThan(float number, float max) {
		if (number >= max)
			throw new IllegalArgumentException("Provided number should be less than " + max + ". Provided: " + number);
	}

	public static void lessOrEqualsThan(int number, int max) {
		if (number > max)
			throw new IllegalArgumentException("Provided number should be less or equals " + max + ". Provided: " + number);
	}

	public static void lessOrEqualsThan(float number, float max) {
		if (number > max)
			throw new IllegalArgumentException("Provided number should be less or equals " + max + ". Provided: " + number);
	}

	public static <T> void arrayWithLength(T[] arr, int length) {
		Preconditions.checkNotNull(arr);
		if (arr.length != length)
			throw new IllegalArgumentException("Provided array should have length " + length + ". Provided: " + arr.length);
	}

	public static void instanceOf(Object obj, Class<?> clazz) {
		if (!clazz.isInstance(obj)) {
			throw new IllegalArgumentException("Provided object should be an instance of " + clazz + ". Provided: " + obj.getClass());
		}
	}
}
