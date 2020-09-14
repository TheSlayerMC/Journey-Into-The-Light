package ru.timeconqueror.timecore.util.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Wrapper for method, unlocks the access to it.
 *
 * @param <T> method return type.
 */
public class UnlockedMethod<T> {
    private final Method method;

    public UnlockedMethod(Method method) {
        this.method = method;

        ReflectionHelper.setAccessible(method);
    }

    /**
     * Invokes method with given params.
     * Safe for use with non-accessible methods.
     *
     * @param methodOwner owner of method. If the underlying method is static, the methodOwner argument is ignored; it may be null.
     * @param params      the parameters used for the method call.
     * @return object return by method or null if the method has {@code void} modifier.
     */
    public T invoke(Object methodOwner, Object... params) {
        try {
            return (T) method.invoke(methodOwner, params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public Method getMethod() {
        return method;
    }
}
