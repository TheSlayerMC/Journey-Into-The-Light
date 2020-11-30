package net.jitl.util;

public enum EnumRecipePrefix {

	SAPPHIRE("sapphire_"),
	LUNIUM("lunium_"),
	SHADIUM("shadium_");

	String prefix;

	EnumRecipePrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getString() {
		return prefix;
	}
}
