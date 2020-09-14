package ru.timeconqueror.timecore.util.reflection;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Wrapper for field, unlocks the access to it.
 *
 * @param <T> field type.
 */
public class UnlockedField<T> {
    private final Field field;
    private boolean finalized;

    public UnlockedField(Field field) {
        this.field = field;

        ReflectionHelper.setAccessible(field);
        finalized = ReflectionHelper.isFinal(field);
    }

    /**
     * Gets the value of field in provided {@code fieldOwner}
     * Safe for use with non-accessible fields.
     *
     * @param fieldOwner owner of field. If the underlying field is static, the obj argument is ignored; it may be null.
     */
    @SuppressWarnings("unchecked")
    public T get(@Nullable Object fieldOwner) {
        try {
            return (T) field.get(fieldOwner);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets new value into field of provided {@code fieldOwner}
     * Safe for use with non-accessible or finalized fields.
     * <br><br>
     * <font color="yellow">Warning</font>:
     * Values in <i>static final</i> fields are inlined in places of calling,
     * so setting new value won't change anything in most situations.
     * To make it change, the initial value of field shouldn't be known at runtime.
     * <br>
     * Example:<br>
     * Value setting will do nothing with this:
     * <pre>private static final boolean CONSTANT_FOREVER = false;</pre>
     * But it will work with this:
     * <pre>private static final boolean CHANGEABLE_CONSTANT = Boolean.parseBoolean("false");</pre>
     * because the second one can't be known at runtime and will be calculated.
     *
     * @param fieldOwner owner of field. If the underlying field is static, the methodOwner argument is ignored; it may be null.
     * @param newVal     new value to put in the field of provided {@code fieldOwner}
     */
    public void set(@Nullable Object fieldOwner, T newVal) {
        if (finalized) {
            try {
                ReflectionHelper.unfinalize(field);
                finalized = false;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        try {
            field.set(fieldOwner, newVal);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Field getField() {
        return field;
    }

    /**
     * Returns true, if provided field is static, otherwise returns false.
     */
    public boolean isStatic() {
        return Modifier.isStatic(field.getModifiers());
    }
}
