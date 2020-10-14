package net.journey.dimension.base.biome;

public enum EnumBiomeColor {

	CORBA_FOREST(0x749f00),
	CORBA_SWAMP(0x6daa2c),
	CORBA_PLAINS(0x86b800),

	EUCA_GOLDITE(0x72b011),
	EUCA_GOLD(0xffdb13),
	EUCA_SILVER(0xffffff);

	private final int color;

	EnumBiomeColor(int hexColor) {
		this.color = hexColor;
	}

	public int getInt() {
		return color;
	}
}
