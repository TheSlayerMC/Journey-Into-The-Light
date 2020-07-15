package net.journey.integration.tconstruct;

public enum EnumJTCMaterials {

	CELESTIUM("celestium", 0x00ffb4);

	private final String name;
	private final int color;

	EnumJTCMaterials(String name, int color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return this.name;
	}

	public int getColor() {
		return this.color;
	}
}
