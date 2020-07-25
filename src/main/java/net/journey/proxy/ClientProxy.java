package net.journey.proxy;

import net.journey.JITL;
import net.journey.client.PlayerStats;
import net.journey.client.handler.BossTickHandler;
import net.journey.client.handler.GuiHandler;
import net.journey.client.render.EntityRendering;
import net.journey.client.render.RenderEssenceBar;
import net.journey.client.render.particles.OreParticleFX;
import net.journey.dimension.boil.BoilSkyRenderer;
import net.journey.dimension.cloudia.CloudiaSkyRenderer;
import net.journey.enums.EnumParticlesClasses;
import net.journey.eventhandler.ClientTickEvent;
import net.journey.util.gen.lang.LangGeneratorFacade;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.slayer.api.SlayerAPI;

public class ClientProxy extends CommonProxy {

    public static final IRenderHandler boilSkyRenderer = new BoilSkyRenderer();
    public static final IRenderHandler cloudiaSkyRenderer = new CloudiaSkyRenderer();

    @Override
    public EntityPlayer getPlayer() {
        return FMLClientHandler.instance().getClientPlayerEntity();
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(JITL.instance, new GuiHandler());

        super.preInit(event);

        EntityRendering.preInit();
        SlayerAPI.registerEventListener(new RenderEssenceBar());
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        EntityRendering.init();
        SlayerAPI.registerEventListener(new BossTickHandler());
        SlayerAPI.registerEventListener(new ClientTickEvent());
        SlayerAPI.registerEventListener(new PlayerStats());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);

        LangGeneratorFacade.save();
    }

    @Override
    @Deprecated //use WorldUtils#spawnParticles, but don't use it on server side, because it will crash
    public void spawnParticle(EnumParticlesClasses particle, World world, double posX, double posY, double posZ, boolean b) {
        if (world.isRemote) {
            try {
                Particle fx = null;
                if (b) {
                    fx = particle.getParticle().getConstructor(World.class, double.class, double.class, double.class).newInstance(world, posX, posY, posZ);
                } else {
                    fx = particle.getParticle().getConstructor(World.class, double.class, double.class, double.class, double.class, double.class, double.class).newInstance(world, posX, posY, posZ, 0D, 0D, 0D);
                }
                FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @Deprecated //use WorldUtils#spawnParticles, but don't use it on server side, because it will crash
    public void spawnParticle(EnumParticlesClasses particle, World worldObj, double posX, double posY, double posZ, double posX1, double posY1, double posZ1) {
        try {
            Particle fx = particle.getParticle().getConstructor(World.class, double.class, double.class, double.class, double.class, double.class, double.class).newInstance(worldObj, posX, posY, posZ, posX1, posY1, posZ1);
            Minecraft.getMinecraft().effectRenderer.addEffect(fx);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Deprecated //use WorldUtils#spawnParticles, but don't use it on server side, because it will crash
    public void spawnOreParticle(World w, double x, double y, double z, float r, float g, float b) {
        OreParticleFX fx = new OreParticleFX(w, x, y, z, r, g, b);
        if (fx != null)
            Minecraft.getMinecraft().effectRenderer.addEffect(fx);

    }
}