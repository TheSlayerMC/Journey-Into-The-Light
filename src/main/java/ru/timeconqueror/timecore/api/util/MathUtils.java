package ru.timeconqueror.timecore.api.util;

//from net.minecraft.util.math.MathHelper 1.14.4
public class MathUtils {
	public static float lerp(float pct, float start, float end) {
		return start + pct * (end - start);
	}

	public static double lerp(double pct, double start, double end) {
		return start + pct * (end - start);
	}
}
