package net.jitl.common.helper;

import mcp.MethodsReturnNonnullByDefault;
import net.jitl.common.item.gearabilities.BaseAbilities;
import net.jitl.common.item.gearabilities.CelestiumAbilities;
import net.jitl.common.item.gearabilities.ShadiumAbilities;
import net.jitl.init.JItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

public enum JToolTiers implements IItemTier {
    SAPPHIRE(1461, 10F, 8F, 5F, 3F, 1.6F, JItems.SAPPHIRE, null),
    SHADIUM(1461, 10F, 8F, 5F, 3F, 1.6F, JItems.SHADIUM_INGOT, ShadiumAbilities.class),
    LUNIUM(1490, 12F, 8F, 6F, 4F, 1.7F, JItems.LUNIUM_INGOT, null),
    BLOODCRUST(1490, 12F, 8F, 6F, 4F, 1.7F, JItems.BLOODCRUST_INGOT, null),
    CELESTIUM(1490, 12F, 8F, 6F, 4F, 1.7F, JItems.CELESTIUM_INGOT, CelestiumAbilities.class),
    KORITE(1490, 12F, 8F, 6F, 4F, 1.7F, JItems.KORITE_INGOT, null),
    MEKYUM(1490, 12F, 8F, 6F, 4F, 1.7F, JItems.MEKYUM_INGOT, null),
    STORON(1490, 12F, 8F, 6F, 4F, 1.7F, JItems.STORON_INGOT, null);

    private final int level;
    private final int uses;
    private final float speed;
    /**
     * Swords are casted to int so cant do half damage
     **/
    private final float swordDamage, axeDam, shovelDam;
    private final int enchantmentValue;
    private final float attackSpeed;
    private final Ingredient repairIngredient;
    private final Class<? extends BaseAbilities> ability;

    JToolTiers(int harvest, int uses, float eff, float swordDam, float axeDam, float shovelDam, float attackSpeed, Item repair, Class<? extends BaseAbilities> abilityManager) {
        this.level = harvest;
        this.uses = uses;
        this.speed = eff;
        this.swordDamage = swordDam;
        this.axeDam = axeDam;
        this.shovelDam = shovelDam;
        this.enchantmentValue = 30;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = Ingredient.of(repair);
        ability = abilityManager;
    }

    JToolTiers(int uses, float eff, float swordDam, float axeDam, float shovelDam, float attackSpeed, Item repair, Class<? extends BaseAbilities> abilityManager) {
        this(3, uses, eff, swordDam, axeDam, shovelDam, attackSpeed, repair, abilityManager);
    }

    public float getSwordDamage() {
        return swordDamage;
    }

    public float getAxeDam() {
        return axeDam;
    }

    public float getShovelDam() {
        return shovelDam;
    }

    public float getAttackSpeed() {
        return attackSpeed - 4F;
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
        return -1;//fuck that off
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @MethodsReturnNonnullByDefault
    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }

    public BaseAbilities getAbilities() {
        try {
            return ability != null ? ability.newInstance() : null; //TODO: figure out what to do with instances
        } catch (Exception e) {
            return null;
        }
    }
}
