package net.jitl.init;

import net.jitl.JITL;
import net.jitl.client.particle.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import ru.timeconqueror.timecore.api.registry.SimpleForgeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JParticleManager {

    @AutoRegistrable
    public static final SimpleForgeRegister<ParticleType<?>> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.PARTICLE_TYPES, JITL.MODID);

    public static final RegistryObject<BasicParticleType> RED_FLAME = REGISTER.register("red_flame", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> MUD = REGISTER.register("mud", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> CONJURING = REGISTER.register("conjuring", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> CAVE_VINE = REGISTER.register("cave_vine", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> ESSENCIA_LIGHTNING = REGISTER.register("essencia_lightning", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> MINERS_PEARL = REGISTER.register("miners_pearl", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> SNOWFLAKE = REGISTER.register("snowflake", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> SULPHUR = REGISTER.register("sulphur", () -> new BasicParticleType(false));

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        ParticleManager manager = minecraft.particleEngine;
        manager.register(RED_FLAME.get(), RedFlameParticle.Factory::new);
        manager.register(MUD.get(), MudParticle.Factory::new);
        manager.register(CONJURING.get(), ConjuringParticle.Factory::new);
        manager.register(CAVE_VINE.get(), CaveVineParticle.Factory::new);
        manager.register(ESSENCIA_LIGHTNING.get(), EssenciaLightningParticle.Factory::new);
        manager.register(MINERS_PEARL.get(), MinersPearlParticle.Factory::new);
        manager.register(SNOWFLAKE.get(), SnowflakeParticle.Factory::new);
        manager.register(SULPHUR.get(), SulphurParticle.Factory::new);
    }
}
