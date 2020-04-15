package net.journey.items;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.client.ArmorDescription;
import net.journey.util.EnumArmor;
import net.journey.util.LangRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.slayer.api.SlayerAPI;
import net.slayer.api.SlayerAPI.Colour;

import java.util.List;

public class ItemModArmor extends ItemArmor implements ISpecialArmor {

    protected final EntityEquipmentSlot HEAD = EntityEquipmentSlot.HEAD, BODY = EntityEquipmentSlot.CHEST, LEGS = EntityEquipmentSlot.LEGS, BOOTS = EntityEquipmentSlot.FEET;
    protected double damageReduction;
    protected boolean unbreakable;
    protected String textureName = SlayerAPI.PREFIX + "textures/models/armor/", name;
    protected int fullReduction;
    protected EnumArmor armorMaterial;

    public ItemModArmor(EnumArmor armorMaterial, EntityEquipmentSlot type) {
        this(armorMaterial, type, armorMaterial.getType());
    }

    public ItemModArmor(EnumArmor armorMaterial, EntityEquipmentSlot type, String name) {
        super(armorMaterial.getArmorMaterial(), type.getIndex(), type);
        this.armorMaterial = armorMaterial;
        this.fullReduction = armorMaterial.getDamageReduction();
        if (armorType == EntityEquipmentSlot.HEAD) damageReduction = ((((double) fullReduction) / 24) * 5) / 100;
        else if (armorType == EntityEquipmentSlot.CHEST) damageReduction = ((((double) fullReduction) / 24) * 8) / 100;
        else if (armorType == EntityEquipmentSlot.LEGS) damageReduction = ((((double) fullReduction) / 24) * 7) / 100;
        else if (armorType == EntityEquipmentSlot.FEET) damageReduction = ((((double) fullReduction) / 24) * 4) / 100;
        this.unbreakable = armorMaterial.isUndamageable();
        setArmorType(name, armorType);
        setCreativeTab(JourneyTabs.armor);
        setTranslationKey(this.name);
        JourneyItems.itemNames.add(SlayerAPI.PREFIX + name.toLowerCase());
        JourneyItems.items.add(this);
        LangRegistry.addArmour(this, armorMaterial, type);
        setRegistryName(SlayerAPI.MOD_ID, this.name);
    }

    @Override
    public boolean getIsRepairable(ItemStack i, ItemStack i1) {
        return armorMaterial.getRepairItem() != null && armorMaterial.getRepairItem() == i1.getItem() || super.getIsRepairable(i, i1);
    }

    protected void setArmorType(String material, EntityEquipmentSlot armorType) {
        this.name = armorType == HEAD ? material + "Helmet" : armorType == BODY ? material + "Body" : armorType == LEGS ? material + "Legs" : armorType == BOOTS ? material + "Boots" : material + "Unknown";
        this.textureName = (armorType == EntityEquipmentSlot.HEAD || armorType == EntityEquipmentSlot.CHEST || armorType == EntityEquipmentSlot.FEET) ? textureName + armorMaterial.getType() + "_1.png" : textureName + armorMaterial.getType() + "_2.png";
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return textureName;
    }

    @Override
    public void addInformation(ItemStack item, World worldIn, List<String> list, ITooltipFlag flagIn) {
        double roundPH = Math.round(damageReduction * 1000);
        double roundedDamage = roundPH / 10;
        list.add(damageReduction == 0.0 ? (Colour.DARK_AQUA + "No Protection") : Colour.AQUA + "Damage Reduction: " + roundedDamage);
        list.add(!unbreakable ? (item.getMaxDamage() - item.getItemDamage() + " Uses Remaining") : "Unlimited Uses");
        ArmorDescription.add(item, list);
    }

    @Override
    public boolean isDamageable() {
        return !unbreakable;
    }

    @Override
    public void damageArmor(EntityLivingBase l, ItemStack s, DamageSource d, int amount, int slot) {
        if (!unbreakable) s.damageItem(1, l);
    }

    @Override
    public int getArmorDisplay(EntityPlayer p, ItemStack s, int b) {
        return (int) Math.round((damageReduction * 100) / 4);
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase l, ItemStack s, DamageSource d, double amount, int slot) {
        return new ISpecialArmor.ArmorProperties(0, damageReduction, 50000);
    }
}