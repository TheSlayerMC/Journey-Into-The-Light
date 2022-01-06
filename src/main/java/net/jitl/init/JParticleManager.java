package net.jitl.init;

import net.jitl.JITL;
import net.jitl.client.particle.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;
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

    public static final RegistryObject<SimpleParticleType> RED_FLAME = REGISTER.register("red_flame", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> MUD = REGISTER.register("mud", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> CONJURING = REGISTER.register("conjuring", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> CAVE_VINE = REGISTER.register("cave_vine", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> ESSENCIA_LIGHTNING = REGISTER.register("essencia_lightning", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> MINERS_PEARL = REGISTER.register("miners_pearl", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> SNOWFLAKE = REGISTER.register("snowflake", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> SULPHUR = REGISTER.register("sulphur", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> CRYSTAL_FRUIT = REGISTER.register("crystal_fruit", () -> new SimpleParticleType(false));

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        ParticleEngine manager = minecraft.particleEngine;
        manager.register(RED_FLAME.get(), RedFlameParticle.Factory::new);
        manager.register(MUD.get(), MudParticle.Factory::new);
        manager.register(CONJURING.get(), ConjuringParticle.Factory::new);
        manager.register(CAVE_VINE.get(), CaveVineParticle.Factory::new);
        manager.register(ESSENCIA_LIGHTNING.get(), EssenciaLightningParticle.Factory::new);
        manager.register(MINERS_PEARL.get(), MinersPearlParticle.Factory::new);
        manager.register(SNOWFLAKE.get(), SnowflakeParticle.Factory::new);
        manager.register(SULPHUR.get(), SulphurParticle.Factory::new);
        manager.register(CRYSTAL_FRUIT.get(), CrystalFruitParticle.Factory::new);
    }
}
