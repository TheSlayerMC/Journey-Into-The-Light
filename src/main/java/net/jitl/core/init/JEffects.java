package net.jitl.core.init;

import net.jitl.common.effect.EssenceRegenEffect;
import net.jitl.common.effect.FrostburnEffect;
import net.jitl.core.JITL;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.timeconqueror.timecore.api.registry.SimpleForgeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JEffects {
    @AutoRegistrable
    public static SimpleForgeRegister<MobEffect> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.MOB_EFFECTS, JITL.MODID);

    public static final RegistryObject<MobEffect> ESSENCE_REGEN =
            REGISTER.register("essence_regen", () -> new EssenceRegenEffect(MobEffectCategory.BENEFICIAL, 0xff3600));

    public static final RegistryObject<MobEffect> FROSTBURN =
            REGISTER.register("frostburn", () -> new FrostburnEffect(MobEffectCategory.BENEFICIAL, 0x99e6ff));

    public static class JPotions {
        @AutoRegistrable
        public static SimpleForgeRegister<Potion> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.POTIONS, JITL.MODID);

        public static final RegistryObject<Potion> ESSENCE_REGEN =
                REGISTER.register("essence_regen", () -> new Potion("essence_regen", new MobEffectInstance(JEffects.ESSENCE_REGEN.get(), 900, 1)));

        public static final RegistryObject<Potion> LONG_ESSENCE_REGEN =
                REGISTER.register("long_essence_regen", () -> new Potion("long_essence_regen", new MobEffectInstance(JEffects.ESSENCE_REGEN.get(), 1800, 1)));

        public static final RegistryObject<Potion> STRONG_ESSENCE_REGEN =
                REGISTER.register("strong_essence_regen", () -> new Potion("strong_essence_regen", new MobEffectInstance(JEffects.ESSENCE_REGEN.get(), 450, 3)));

        public static final RegistryObject<Potion> FROSTBURN =
                REGISTER.register("frostburn", () -> new Potion("frostburn", new MobEffectInstance(JEffects.FROSTBURN.get(), 3600, 1)));

        public static final RegistryObject<Potion> LONG_FROSTBURN =
                REGISTER.register("long_frostburn", () -> new Potion("long_frostburn", new MobEffectInstance(JEffects.FROSTBURN.get(), 9600, 1)));
    }
}