package net.jitl.common.helper;

import mcp.MethodsReturnNonnullByDefault;
import net.jitl.JITL;
import net.jitl.init.JItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public enum JArmorMaterial implements IArmorMaterial {

    //Change these
    SAPPHIRE("sapphire", 27, new int[]{3, 6, 8, 3}, 0.5F, 0.2F, SoundEvents.ARMOR_EQUIP_IRON, JItems.SAPPHIRE),
    LUNIUM("lunium", 33, new int[]{3, 6, 8, 3}, 2.0F, 0.0F, SoundEvents.ARMOR_EQUIP_IRON, JItems.LUNIUM_INGOT),
    SHADIUM("shadium", 33, new int[]{3, 6, 8, 3}, 2.0F, 0.0F, SoundEvents.ARMOR_EQUIP_IRON, JItems.SHADIUM_INGOT),
    BLOODCRUST("bloodcrust", 27, new int[]{3, 6, 8, 3}, 0.5F, 0.2F, SoundEvents.ARMOR_EQUIP_IRON, JItems.BLOODCRUST_INGOT),
    CELESTIUM("celestium", 27, new int[]{2, 5, 7, 3}, 0F, 0.2F, SoundEvents.ARMOR_EQUIP_IRON, JItems.CELESTIUM_INGOT),

    DREADIRON("dreadiron", 24, new int[]{2, 5, 6, 2}, 1.0F, 0.2F, SoundEvents.ARMOR_EQUIP_IRON, JItems.DREADIRON_INGOT);

    private final String name;
    private final int durabilityMultiplier;
    private final float toughness, knockback;
    private final int[] protectionPerPiece;
    private final SoundEvent slotIn;
    private final Ingredient repairItem;
    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};

    JArmorMaterial(String name, int dur, int[] protection, float toughness, float knockback, SoundEvent putIn, Item repair) {
        this.name = name;
        this.durabilityMultiplier = dur;
        this.protectionPerPiece = protection;
        this.slotIn = putIn;
        this.toughness = toughness;
        this.knockback = knockback;
        this.repairItem = Ingredient.of(repair);
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlotType slot) {
        return BASE_DURABILITY[slot.getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType slotIn) {
        return protectionPerPiece[slotIn.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return 30;
    }

    @MethodsReturnNonnullByDefault
    @Override
    public SoundEvent getEquipSound() {
        return slotIn;
    }

    @MethodsReturnNonnullByDefault
    @Override
    public Ingredient getRepairIngredient() {
        return repairItem;
    }

    /**
     * This sets the model texture name
     */
    @Override
    public String getName() {
        return JITL.MODID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockback;
    }
}