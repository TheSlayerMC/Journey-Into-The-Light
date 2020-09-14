package net.journey.util;

import net.minecraft.launchwrapper.Launch;

public class Environment {
	public static boolean isInDev() {
		return (boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
	}
}
