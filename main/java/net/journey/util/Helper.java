package net.journey.util;

import net.minecraft.util.math.BlockPos;

public class Helper {

	public static void printCoords(int x, int y, int z) {
		System.out.println("X: " + x + ", Y: " + y + ", Z:" + z);
	}
	
	public static void printCoords(double x, double y, double z) {
		System.out.println("X: " + x + ", Y: " + y + ", Z:" + z);
	}
	
	public static void printCoords(BlockPos pos) {
		System.out.println("X: " + pos.getX() + ", Y: " + pos.getY() + ", Z:" + pos.getZ());
	}
	
	public static void print(Object o) {
		System.out.println(o);
	}
}