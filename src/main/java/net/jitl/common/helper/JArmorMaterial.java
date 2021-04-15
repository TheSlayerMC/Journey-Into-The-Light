package net.jitl.common.helper;

import mcp.MethodsReturnNonnullByDefault;
import net.jitl.JITL;
import net.jitl.common.item.gearabilities.FullArmorAbilities;
import net.jitl.common.item.gearabilities.PieceArmorAbilities;
import net.jitl.common.item.gearabilities.celestium.CelestiumFullArmorAbilities;
import net.jitl.common.item.gearabilities.shadium.ShadiumPieceArmorAbilities;
import net.jitl.init.JItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public enum JArmorMaterial implements IArmorMaterial {

    //Change these
    SAPPHIRE("sapphire", 27, new int[]{3, 6, 8, 3}, 0.5F, 0.2F, SoundEvents.ARMOR_EQUIP_IRON, JItems.SAPPHIRE, null, null),
    LUNIUM("lunium", 27, new int[]{3, 6, 8, 3}, 0.5F, 0.2F, SoundEvents.ARMOR_EQUIP_IRON, JItems.LUNIUM_INGOT, null, null),
    SHADIUM("shadium", 27, new int[]{3, 6, 8, 3}, 0.5F, 0.2F, SoundEvents.ARMOR_EQUIP_IRON, JItems.SHADIUM_INGOT, ShadiumPieceArmorAbilities.class, null),
    BLOODCRUST("bloodcrust", 27, new int[]{3, 6, 8, 3}, 0.5F, 0.2F, SoundEvents.ARMOR_EQUIP_IRON, JItems.BLOODCRUST_INGOT, null, null),
    CELESTIUM("celestium", 27, new int[]{2, 5, 7, 3}, 0F, 0.2F, SoundEvents.ARMOR_EQUIP_IRON, JItems.CELESTIUM_INGOT, null, CelestiumFullArmorAbilities.class);

    private final String name;
    private final int durabilityMultiplier;
    private final float toughness, knockback;
    private final int[] protectionPerPiece;
    private final SoundEvent slotIn;
    private final Ingredient repairItem;
    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
    private final Class<? extends PieceArmorAbilities> pieceAbilities;
    private final Class<? extends FullArmorAbilities> fullAbility;

    JArmorMaterial(String name, int dur, int[] protection, float toughness, float knockback, SoundEvent putIn, Item repair, Class<? extends PieceArmorAbilities> pieceAbilities, Class<? extends FullArmorAbilities> fullAbility) {
        this.name = name;
        this.durabilityMultiplier = dur;
        this.protectionPerPiece = protection;
        this.slotIn = putIn;
        this.toughness = toughness;
        this.knockback = knockback;
        this.repairItem = Ingredient.of(repair);
        this.pieceAbilities = pieceAbilities;
        this.fullAbility = fullAbility;
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

    public PieceArmorAbilities getPieceAbility() {
        try {
            return this.pieceAbilities != null ? pieceAbilities.newInstance() : null;
        } catch (Exception e) {
            return null;
        }
    }

    public FullArmorAbilities getFullAbility() {
        try {
            return this.fullAbility != null ? fullAbility.newInstance() : null;
        } catch (Exception e) {
            return null;
        }
    }

    public Class<? extends PieceArmorAbilities> getPieceAbilityClass() {
        return this.pieceAbilities;
    }

    public Class<? extends FullArmorAbilities> getFullAbilityClass() {
        return this.fullAbility;
    }
}