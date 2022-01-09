package net.jitl.client.util;

public class Rectangle {

    private final int left;
    private final int top;
    private final int right;
    private final int bottom;

    public Rectangle(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public static Rectangle createWithWidthHeight(int left, int top, int width, int height) {
        return new Rectangle(left, top, left + width, top + height);
    }

    public int width() {
        return right - left;
    }

    public int height() {
        return bottom - top;
    }

    public int right() {
        return right;
    }

    public int left() {
        return left;
    }

    public int top() {
        return top;
    }

    public int bottom() {
        return bottom;
    }
}