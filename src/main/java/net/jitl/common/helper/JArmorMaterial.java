package net.jitl.common.helper;

import net.jitl.init.JItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public enum JArmorMaterial implements IArmorMaterial {

    SAPPHIRE("sapphire", 27, new int[] {3, 6, 8, 3}, 0.5F, 0.2F, SoundEvents.ARMOR_EQUIP_IRON, JItems.SAPPHIRE);

    private final String NAME;
    private final int DURABILITY_MULTIPLIER;
    private final float TOUGHNESS, KNOCKBACK;
    private final int[] PROTECTION_PER_PIECE;
    private final SoundEvent SLOT_IN;
    private final Ingredient REPAIR_ITEM;
    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};

    private JArmorMaterial(String name, int dur, int[] protection, float toughness, float knockback, SoundEvent putIn, Item repair) {
        this.NAME = name;
        this.DURABILITY_MULTIPLIER = dur;
        this.PROTECTION_PER_PIECE = protection;
        this.SLOT_IN = putIn;
        this.TOUGHNESS = toughness;
        this.KNOCKBACK = knockback;
        this.REPAIR_ITEM = Ingredient.of(repair);
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlotType slot) {
        return HEALTH_PER_SLOT[slot.getIndex()] * this.DURABILITY_MULTIPLIER;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType slotIn) {
        return PROTECTION_PER_PIECE[slotIn.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return 30;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SLOT_IN;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return REPAIR_ITEM;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.TOUGHNESS;
    }

    @Override
    public float getKnockbackResistance() {
        return this.KNOCKBACK;
    }
}