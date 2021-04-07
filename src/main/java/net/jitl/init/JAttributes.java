package net.jitl.init;

import net.jitl.JITL;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import ru.timeconqueror.timecore.api.registry.SimpleForgeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JAttributes {
    @AutoRegistrable
    public static SimpleForgeRegister<Attribute> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.ATTRIBUTES, JITL.MODID);

    //TODO: During development, default values are set so we don't have to keep equipping curios to test essence. In public builds, they should be set to zero.
    public static final RegistryObject<Attribute> MAX_ESSENCE =
            REGISTER.register("max_essence", () -> new RangedAttribute("jitl.max_essence", 1.0F, 0.0D, 10.0D).setSyncable(true));

    public static final RegistryObject<Attribute> ESSENCE_REGEN_SPEED =
            REGISTER.register("essence_regen_speed", () -> new RangedAttribute("jitl.essence_regen_speed", 0.012F, 0.0D, 0.065F).setSyncable(true));
}
