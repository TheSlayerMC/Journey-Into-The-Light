package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.effect.EssenceRegenEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import ru.timeconqueror.timecore.api.registry.SimpleForgeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JPotions {
    @AutoRegistrable
    public static SimpleForgeRegister<Effect> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.POTIONS, JITL.MODID);

    public static final RegistryObject<Effect> ESSENCE_REGEN =
            REGISTER.register("essence_regen", () -> new EssenceRegenEffect(EffectType.BENEFICIAL, 0xff3600));

    public static class JPotionEffects {
        @AutoRegistrable
        public static SimpleForgeRegister<Potion> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.POTION_TYPES, JITL.MODID);

        public static final RegistryObject<Potion> ESSENCE_REGEN =
                REGISTER.register("essence_regen", () -> new Potion("essence_regen", new EffectInstance(JPotions.ESSENCE_REGEN.get(), 100, 3)));
    }
}
