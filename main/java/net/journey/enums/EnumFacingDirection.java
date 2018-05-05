package net.journey.enums;

public enum EnumFacingDirection {
	NORTH(1),
	EAST(2),
	SOUTH(3),
	WEST(4);
	public int number;
	private EnumFacingDirection(int number) {
		this.number = number;
	}
}
