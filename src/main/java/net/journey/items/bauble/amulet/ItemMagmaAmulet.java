package net.journey.items.bauble.amulet;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.journey.items.base.JItem;
import net.journey.util.PotionEffects;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import java.util.List;

public class ItemMagmaAmulet extends JItem implements IBauble {

    public ItemMagmaAmulet() {
        setMaxStackSize(1);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.AMULET;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if(player.isInLava()) {
            player.addPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 10, 4));
            player.motionX *= 1.75F;
            player.motionZ *= 1.75F;
        }
        if(player.isInWater()) {
            player.addPotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 10, 4));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
        l.add(SlayerAPI.Colour.GOLD + "Grants agility and fire resistance while in lava");
        l.add(SlayerAPI.Colour.RED + "Poisons while in water");
    }
}
