package net.journey.items.bauble.ring;

import baubles.common.items.ItemRing;
import net.journey.util.PotionEffects;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import java.util.List;

public class ItemRingPoison extends ItemRingBase {

    public ItemRingPoison(String name, String enName) {
        super(name, enName);
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        Potion potion = Potion.getPotionById(PotionEffects.poison);
        if (player.isPotionActive(potion)) {
            player.removeActivePotionEffect(potion);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
        l.add(SlayerAPI.Colour.LIGHT_PURPLE + "Negates Poison");
    }
}
