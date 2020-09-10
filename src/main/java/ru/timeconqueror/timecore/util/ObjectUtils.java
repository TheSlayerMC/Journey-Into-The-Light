package ru.timeconqueror.timecore.util;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ObjectUtils {
	public static <T> void doIfNotNull(T obj, Consumer<@NotNull T> action) {
		if (obj != null) {
			action.accept(obj);
		}
	}
}
