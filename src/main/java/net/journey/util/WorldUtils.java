package net.journey.util;

import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldUtils {
	@SideOnly(Side.CLIENT)
	public static void spawnParticle(Particle particle, World world) {
		if (world.isRemote) {
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(particle);
		}
	}
}
