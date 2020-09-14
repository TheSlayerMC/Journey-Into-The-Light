package net.journey.util;

public enum EnumHexColorHelper {

	GREEN(0x00ff2a),
	DARK_GREEN(0x00a41b),

	TURQUOISE(0x00ffc6),
	LIGHT_BLUE(0x00fff6),
	BLUE(0x006cff),
	DARK_BLUE(0x00079f),

	RED(0xff0000),
	DARK_RED(0xa00000),

	ORANGE(0xff9c00),
	DARK_ORANGE(0xb36100),

	YELLOW(0xfff600),
	DARK_YELLOW(0xb3ad00),

	PURPLE(0x9000ff),
	PINK(0xfc00ff),

	WHITE(0xffffff),
	LIGHT_GRAY(0xc8c8c8),
	GRAY(0x8a8a8a),
	DARK_GRAY(0x4d4d4d),
	BLACK(0x000000);

	private final int color;

	EnumHexColorHelper(int hexColor) {
		this.color = hexColor;
	}

	public int getInt() {
		return color;
	}
}
