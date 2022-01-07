package net.jitl.common.helper;

public enum EnumHarvestLevel {
	WOOD(0),
	STONE(1),
	IRON(2),
	DIAMOND(3),
	NETHERITE(4);

	private final int level;

	EnumHarvestLevel(int level) {
		this.level = level;
	}

	public int getInt() {
		return level;
	}
}
