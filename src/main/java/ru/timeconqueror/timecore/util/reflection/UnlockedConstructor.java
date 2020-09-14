package ru.timeconqueror.timecore.util.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Wrapper for constructor, unlocks the access to it.
 *
 * @param <T> type of instances that are created by this constructor.
 */
public class UnlockedConstructor<T> {
    private final Constructor<T> c;

    public UnlockedConstructor(Constructor<T> c) {
        this.c = c;
        ReflectionHelper.setAccessible(c);
    }

    /**
     * Creates a new instance.
     * Safe for use with non-accessible constructors.
     *
     * @param initParams an array of objects to be passed as parameters to the constructor call.
     * @return new instance.
     */
    public T newInstance(Object... initParams) {
        try {
            return c.newInstance(initParams);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public Constructor<T> getConstructor() {
        return c;
    }
}
