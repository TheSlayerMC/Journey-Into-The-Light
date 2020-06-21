package net.journey.items.bauble;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.journey.init.JourneyTabs;
import net.journey.items.base.JItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import java.util.List;
import java.util.UUID;

public class ItemIceAmulet extends JItem implements IBauble {

    protected static final UUID SPEED_MODIFIER = UUID.fromString("758787ea-2eda-4941-8f41-4e3efd1a95a7");
    protected static final UUID DAMAGE_MODIFIER = UUID.fromString("b0d292cf-74cd-4c6e-925f-eb81e78e3582");
    protected static final UUID ATTACK_MODIFIER = UUID.fromString("c0d86a67-553d-4c53-9f68-d8df3a891d38");

    protected static final AttributeModifier speedMod = new AttributeModifier(SPEED_MODIFIER, "Speed Modifier", 0.2F, 0);
    protected static final AttributeModifier damageMod = new AttributeModifier(DAMAGE_MODIFIER, "Damage Modifier", 1.0F, 0);
    protected static final AttributeModifier attackMod = new AttributeModifier(ATTACK_MODIFIER, "Attack Speed Modifier", 0.5F, 0);

    public ItemIceAmulet(String name, String enName) {
        super(name, enName);
        setCreativeTab(JourneyTabs.UTIL);
        setMaxStackSize(1);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.AMULET;
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
        if (player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(speedMod)
                && player.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).hasModifier(damageMod)
                && player.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).hasModifier(attackMod)) {
            player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(speedMod);
            player.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(damageMod);
            player.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier(attackMod);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
        l.add(SlayerAPI.Colour.AQUA + "Grants various buffs while in cold biomes:");
        l.add(SlayerAPI.Colour.LIGHT_PURPLE + "Damage boost");
        l.add(SlayerAPI.Colour.LIGHT_PURPLE + "Speed boost");
        l.add(SlayerAPI.Colour.LIGHT_PURPLE + "Faster attack speed");
    }
}
