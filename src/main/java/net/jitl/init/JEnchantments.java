package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.enchantment.AmbitEnchantment;
import net.jitl.common.enchantment.LightweightEnchantment;
import net.jitl.common.enchantment.ScorchingEnchantment;
import net.jitl.common.item.throwable.PiercerItem;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import ru.timeconqueror.timecore.api.registry.SimpleForgeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JEnchantments {

    public static EnchantmentType PIERCER = EnchantmentType.create("piercer", (item) -> item instanceof PiercerItem);

    @AutoRegistrable
    public static SimpleForgeRegister<Enchantment> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.ENCHANTMENTS, JITL.MODID);

    public static final RegistryObject<Enchantment> CUTTING =
            REGISTER.register("cutting", () -> new DamageEnchantment(Enchantment.Rarity.COMMON, 0, EquipmentSlotType.MAINHAND));

    public static final RegistryObject<Enchantment> LIGHTWEIGHT =
            REGISTER.register("lightweight", () -> new LightweightEnchantment(Enchantment.Rarity.COMMON, PIERCER, EquipmentSlotType.MAINHAND));

    public static final RegistryObject<Enchantment> AMBIT =
            REGISTER.register("ambit", () -> new AmbitEnchantment(Enchantment.Rarity.COMMON, PIERCER, EquipmentSlotType.MAINHAND));

    public static final RegistryObject<Enchantment> SCORCHING =
            REGISTER.register("scorching", () -> new ScorchingEnchantment(Enchantment.Rarity.COMMON, PIERCER, EquipmentSlotType.MAINHAND));
}
