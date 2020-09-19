package net.journey.client.util;

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

	public int getLeft() {
		return left;
	}

	public int getRight() {
		return right;
	}

	public int getTop() {
		return top;
	}

	public int getBottom() {
		return bottom;
	}

	public int getWidth() {
		return right - left;
	}

	public int getHeight() {
		return bottom - top;
	}
}
