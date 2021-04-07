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

    public static final RegistryObject<Attribute> MAX_ESSENCE =
            REGISTER.register("max_essence", () -> new RangedAttribute("jitl.max_essence", 0.0D/*TODO: change to 0 */, 0.0D, 10.0D).setSyncable(true));

}
