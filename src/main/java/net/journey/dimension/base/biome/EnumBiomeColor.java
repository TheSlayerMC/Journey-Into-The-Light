package net.journey.dimension.base.biome;

public enum EnumBiomeColor {

	//Corba
	CORBA_FOREST(0x749f00),
	CORBA_FOREST_2(0x4b9f00),

	CORBA_SWAMP(0x6daa2c),
	CORBA_SWAMP_2(0x3d8222),

	CORBA_PLAINS(0x86b800),
	CORBA_PLAINS_2(0xe4ff00),

	//Euca
	EUCA_GOLDITE(0x72b011),
	EUCA_GOLDITE_2(0x37b011),

	EUCA_GOLD(0xffdb13),

	EUCA_SILVER(0xffffff),
	EUCA_SILVER_2(0xdbdbdb);

	private final int color;

	EnumBiomeColor(int hexColor) {
		this.color = hexColor;
	}

	public int getInt() {
		return color;
	}
}
