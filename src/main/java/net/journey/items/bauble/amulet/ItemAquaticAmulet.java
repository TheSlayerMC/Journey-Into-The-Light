package net.journey.items.bauble.amulet;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.journey.items.bauble.ItemBaubleBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import java.util.List;

public class ItemAquaticAmulet extends ItemBaubleBase implements IBauble {

    public ItemAquaticAmulet() {
        setMaxStackSize(1);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.AMULET;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if(player.isInWater()) {
            player.motionX *= 1.2F;
            player.motionZ *= 1.2F;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
        l.add(SlayerAPI.Colour.AQUA + "Glide through the Tides");
    }
}
