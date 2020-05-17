package ru.timeconqueror.timecore.api.auxiliary;

public class CollectionUtils {
    public static <T> boolean contains(T[] array, T object) {
        for (T t : array) {
            if (t.equals(object)) return true;
        }

        return false;
    }

    public static <T> boolean contains(Iterable<T> list, T object) {
        for (T t : list) {
            if (t.equals(object)) return true;
        }

        return false;
    }
}
