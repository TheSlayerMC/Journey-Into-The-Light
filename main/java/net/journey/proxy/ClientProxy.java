package net.journey.proxy;

import net.journey.JITL;
import net.journey.client.BossTickHandler;
import net.journey.client.EntityRendering;
import net.journey.client.GuiHandler;
import net.journey.client.PlayerStats;
import net.journey.client.RenderHandler;
import net.journey.client.server.DarkEnergyBar;
import net.journey.enums.EnumParticlesClasses;
import net.journey.event.ClientTickEvent;
import net.journey.event.UpdateCheckerEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.slayer.api.SlayerAPI;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerClient() {
		NetworkRegistry.INSTANCE.registerGuiHandler(JITL.instance, new GuiHandler());
		if(!SlayerAPI.DEVMODE) SlayerAPI.registerEvent(new UpdateCheckerEvent());
	}

	@Override
	public void clientPreInit() {
		SlayerAPI.registerEvent(new RenderHandler());
	}

	@Override
	public void clientInit(FMLInitializationEvent event) {
		EntityRendering.init();
		SlayerAPI.registerEvent(new BossTickHandler());
		SlayerAPI.registerEvent(new ClientTickEvent());
		SlayerAPI.registerEvent(new PlayerStats());
	}

	@Override
	public void spawnParticle(EnumParticlesClasses particle, World worldObj, double posX, double posY, double posZ, boolean b) {
		try {
			Particle fx = null;
			if(b) {
				fx = (Particle)particle.getParticle().getConstructor(World.class, double.class, double.class, double.class).newInstance(worldObj, posX, posY, posZ);
			} else {
				fx = (Particle)particle.getParticle().getConstructor(World.class, double.class, double.class, double.class, double.class, double.class, double.class).newInstance(worldObj, posX, posY, posZ, 0D, 0D, 0D);
			}
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateDarkEnergy(int amount) {
		DarkEnergyBar.getProperties(Minecraft.getMinecraft().player).setBarValue(amount);
	}

	@Override
	public void updateEssence(int amount) {
		//EssenceBar.getProperties(Minecraft.getMinecraft().player).setBarValue(amount);
	}

	@Override
	public void updatePower(int amount) {
		//PowerBar.getProperties(Minecraft.getMinecraft().player).setBarValue(amount);
	}
}