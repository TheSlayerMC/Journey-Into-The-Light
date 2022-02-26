package net.jitl.core.init;

import net.jitl.common.loot.modifiers.InjectTableModifier;
import net.jitl.common.loot.modifiers.SmeltingModifier;
import net.jitl.core.JITL;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.timeconqueror.timecore.api.registry.SimpleForgeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JLootModifiers {

    @AutoRegistrable
    public static SimpleForgeRegister<GlobalLootModifierSerializer<?>> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, JITL.MODID);

    public static final RegistryObject<GlobalLootModifierSerializer<InjectTableModifier>> INJECT_TABLE = REGISTER.register("inject_table", InjectTableModifier.Serializer::new);
    public static final RegistryObject<GlobalLootModifierSerializer<SmeltingModifier>> SMELTING = REGISTER.register("smelting", SmeltingModifier.Serializer::new);
}
