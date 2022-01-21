package net.jitl.core.init;

import net.jitl.core.JITL;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.timeconqueror.timecore.api.registry.SimpleForgeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JAttributes {
    @AutoRegistrable
    public static SimpleForgeRegister<Attribute> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.ATTRIBUTES, JITL.MODID);

    //TODO: During development, default values are set so we don't have to keep equipping curios to test essence. In public builds, they should be set to zero.
    public static final RegistryObject<Attribute> MAX_ESSENCE =
            REGISTER.register("max_essence", () -> new RangedAttribute("jitl.max_essence", 1.0F, 0.0F, 20.0F).setSyncable(true));

    public static final RegistryObject<Attribute> ESSENCE_REGEN_SPEED =
            REGISTER.register("essence_regen_speed", () -> new RangedAttribute("jitl.essence_regen_speed", 0.012F, 0.0F, 0.065F).setSyncable(true));

    public static final RegistryObject<Attribute> ESSENCE_BURNOUT =
            REGISTER.register("essence_burnout_time", () -> new RangedAttribute("jitl.essence_burnout_time", 5.0F, 0.0F, 50.0F).setSyncable(false));
}
