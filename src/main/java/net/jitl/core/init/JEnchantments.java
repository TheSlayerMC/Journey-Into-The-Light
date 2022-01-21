package net.jitl.core.init;

import net.jitl.common.enchantment.AmbitEnchantment;
import net.jitl.common.enchantment.FaithfulEnchantment;
import net.jitl.common.enchantment.LightweightEnchantment;
import net.jitl.common.enchantment.ScorchingEnchantment;
import net.jitl.common.item.throwable.PiercerItem;
import net.jitl.core.JITL;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.timeconqueror.timecore.api.registry.SimpleForgeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JEnchantments {

    public static EnchantmentCategory PIERCER = EnchantmentCategory.create("piercer", (item) -> item instanceof PiercerItem);

    @AutoRegistrable
    public static SimpleForgeRegister<Enchantment> REGISTER = new SimpleForgeRegister<>(ForgeRegistries.ENCHANTMENTS, JITL.MODID);

    /*public static final RegistryObject<Enchantment> CUTTING =
            REGISTER.register("cutting", () -> new DamageEnchantment(Enchantment.Rarity.COMMON, 0, EquipmentSlotType.MAINHAND));*/

    public static final RegistryObject<Enchantment> LIGHTWEIGHT =
            REGISTER.register("lightweight", () -> new LightweightEnchantment(Enchantment.Rarity.COMMON, PIERCER, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> AMBIT =
            REGISTER.register("ambit", () -> new AmbitEnchantment(Enchantment.Rarity.COMMON, PIERCER, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> SCORCHING =
            REGISTER.register("scorching", () -> new ScorchingEnchantment(Enchantment.Rarity.COMMON, PIERCER, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> FAITHFUL =
            REGISTER.register("faithful", () -> new FaithfulEnchantment(Enchantment.Rarity.COMMON, PIERCER, EquipmentSlot.MAINHAND));
}
