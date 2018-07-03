package net.journey.proxy;

import net.journey.JITL;
import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.client.BossTickHandler;
import net.journey.client.EntityRendering;
import net.journey.client.GuiHandler;
import net.journey.client.PlayerStats;
import net.journey.client.RenderHandler;
import net.journey.client.server.DarkEnergyBar;
import net.journey.client.server.EssenceBar;
import net.journey.client.server.PowerBar;
import net.journey.enums.EnumParticlesClasses;
import net.journey.event.ClientTickEvent;
import net.journey.event.UpdateCheckerEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemMod;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerClient() {
		NetworkRegistry.INSTANCE.registerGuiHandler(JITL.instance, new GuiHandler());
		if(!SlayerAPI.DEVMODE) SlayerAPI.registerEvent(new UpdateCheckerEvent());
	}

	@Override
	public void clientPreInit() {
		SlayerAPI.addBow(JourneyItems.flameBow, "flameBow");
		SlayerAPI.addBow(JourneyItems.poisonBow, "poisonBow");
		SlayerAPI.addBow(JourneyItems.darknessBow, "darknessBow");
		SlayerAPI.addBow(JourneyItems.frozenBow, "frozenBow");
		SlayerAPI.addBow(JourneyItems.staringBow, "staringBow");
		SlayerAPI.addBow(JourneyItems.deathPiercerBow, "deathPiercerBow");
		SlayerAPI.addBow(JourneyItems.fusionBow, "fusionBow");
		SlayerAPI.addBow(JourneyItems.springBow, "springBow");
		SlayerAPI.addBow(JourneyItems.starlightBow, "starlightBow");
		SlayerAPI.addBow(JourneyItems.wastefulBow, "wastefulBow");
		SlayerAPI.addBow(JourneyItems.flamingBow, "flamingBow");
		SlayerAPI.addBow(JourneyItems.darkEnforcer, "darkEnforcer");
		SlayerAPI.addBow(JourneyItems.depthsBow, "depthsBow");
		SlayerAPI.addBow(JourneyItems.frostbittenBow, "frostbittenBow");
		SlayerAPI.addBow(JourneyItems.frostyBow, "frostyBow");
		SlayerAPI.addBow(JourneyItems.charredBow, "charredBow");
		SlayerAPI.addBow(JourneyItems.fluffyBow, "fluffyBow");
		SlayerAPI.addBow(JourneyItems.golemBow, "golemBow");
		SlayerAPI.addBow(JourneyItems.loggersBow, "loggersBow");
		SlayerAPI.addBow(JourneyItems.overgrownBow, "overgrownBow");
		SlayerAPI.addBow(JourneyItems.overseerBow, "overseerBow");
		SlayerAPI.addBow(JourneyItems.woodlandBow, "woodlandBow");
		SlayerAPI.addBow(JourneyItems.rocsWing, "rocsWing");
		SlayerAPI.addBow(JourneyItems.scaleBow, "scaleBow");
		SlayerAPI.addBow(JourneyItems.mantleBow, "mantleBow");
		SlayerAPI.addBow(JourneyItems.coreExpender, "coreExpender");
		SlayerAPI.addBow(JourneyItems.royalBow, "royalBow");
		SlayerAPI.addBow(JourneyItems.blazingBow, "blazingBow");
		SlayerAPI.addBow(JourneyItems.darkTerraBow, "darkTerraBow");
		SlayerAPI.addBow(JourneyItems.lavenderBow, "lavenderBow");
		SlayerAPI.addBow(JourneyItems.terralightBow, "terralightBow");
		SlayerAPI.addBow(JourneyItems.terrianBow, "terrianBow");
		SlayerAPI.registerEvent(new RenderHandler());

	}

	@Override
	public void clientInit(FMLInitializationEvent event) {
		EntityRendering.init();
		//SlayerAPI.registerEvent(new RenderHandler());
		SlayerAPI.registerEvent(new BossTickHandler());
		SlayerAPI.registerEvent(new ClientTickEvent());
		SlayerAPI.registerEvent(new PlayerStats());
	}

	@Override
	public void registerModModels() {
		
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
		EssenceBar.getProperties(Minecraft.getMinecraft().player).setBarValue(amount);
	}

	@Override
	public void updatePower(int amount) {
		PowerBar.getProperties(Minecraft.getMinecraft().player).setBarValue(amount);
	}
}