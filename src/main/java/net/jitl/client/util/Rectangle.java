package net.jitl.client.util;

public record Rectangle(int left, int top, int right, int bottom) {

    public static Rectangle fromWidthAndHeight(int left, int top, int width, int height) {
        return new Rectangle(left, top, left + width, top + height);
    }

    public int width() {
        return right - left;
    }

    public int height() {
        return bottom - top;
    }
}