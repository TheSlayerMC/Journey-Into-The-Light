package net.journey.util.core;

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
}
