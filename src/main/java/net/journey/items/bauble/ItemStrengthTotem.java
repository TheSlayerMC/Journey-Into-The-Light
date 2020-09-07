package net.journey.items.bauble;

import baubles.api.BaubleType;
import baubles.api.IBauble;
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

public class ItemStrengthTotem extends JItem implements IBauble {

    protected static final UUID KNOCKBACK_MODIFIER = UUID.fromString("21f85109-9fdd-4b25-b48d-0a3d6bf40e84");

    protected static final AttributeModifier knockbackMod = new AttributeModifier(KNOCKBACK_MODIFIER, "Knockback Modifier", 0.6F, 0);

    public ItemStrengthTotem() {
        setMaxStackSize(1);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.TRINKET;
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
        if (!player.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).hasModifier(knockbackMod)) {
            player.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).applyModifier(knockbackMod);
        }
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
        if (player.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).hasModifier(knockbackMod)) {
            player.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).removeModifier(knockbackMod);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
        l.add(SlayerAPI.Colour.AQUA + "Grants increased resistance to knockback");
    }
}
