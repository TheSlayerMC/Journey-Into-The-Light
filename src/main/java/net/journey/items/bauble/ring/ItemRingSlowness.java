package net.journey.items.bauble.ring;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import java.util.List;

public class ItemRingSlowness extends ItemRingBase {

    public ItemRingSlowness(String name, String enName) {
        super(name, enName);
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if (!player.world.isRemote && player.isPotionActive(MobEffects.SLOWNESS)) {
            player.removePotionEffect(MobEffects.SLOWNESS);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
        l.add(SlayerAPI.Colour.LIGHT_PURPLE + "Negates Slowness");
    }
}
