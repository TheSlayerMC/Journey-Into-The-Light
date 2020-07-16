package net.journey.integration.tconstruct;

public enum EnumJTCMaterials {

	LUNIUM("lunium", 0xfffa6f),
	SHADIUM("shadium", 0x3b49ff),

	CELESTIUM("celestium", 0x00ffb4),
	MEKYUM("mekyum", 0xe56cff),
	STORON("storon", 0xe1ea7e),
	KORITE("korite", 0x2bc6ff);

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
