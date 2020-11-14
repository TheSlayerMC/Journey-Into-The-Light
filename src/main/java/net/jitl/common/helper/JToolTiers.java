package net.jitl.common.helper;

import net.jitl.init.JItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum JToolTiers implements IItemTier {

    SAPPHIRE(1461, 10F, 8F, -2.4F, JItems.SAPPHIRE),
    SHADIUM(1461, 10F, 8F, -2.4F, JItems.SHADIUM_INGOT),
    LUNIUM(1490, 12F, 8.5F, -2.4F, JItems.LUNIUM_INGOT);

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final float attackSpeed;
    private final Ingredient repairIngredient;

    private JToolTiers(int harvest, int uses, float eff, float dam, float attackSpeed, Item repair) {
        this.level = harvest;
        this.uses = uses;
        this.speed = eff;
        this.damage = dam - 1;
        this.enchantmentValue = 30;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = Ingredient.of(repair);
    }

    private JToolTiers(int uses, float eff, float dam, float attackSpeed, Item repair) {
        this(3, uses, eff, dam, attackSpeed, repair);
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return damage;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }
}
