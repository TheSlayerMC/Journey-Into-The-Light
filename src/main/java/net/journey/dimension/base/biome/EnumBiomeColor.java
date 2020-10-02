package net.journey.dimension.base.biome;

public enum EnumBiomeColor {

	CORBA_FOREST(0x749f00),
	CORBA_SWAMP(0x509b00),
	CORBA_PLAINS(0x86b800);

	private final int color;

	EnumBiomeColor(int hexColor) {
		this.color = hexColor;
	}

	public int getInt() {
		return color;
	}
}
