package net.journey.items.bauble;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.journey.items.base.JItem;
import net.journey.util.PotionEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ItemTestBauble extends JItem implements IBauble {

    public ItemTestBauble(String name, String enName) {
        super(name, enName);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.AMULET;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        player.addPotionEffect(new PotionEffect(Potion.getPotionById(PotionEffects.blindness)));
    }
}
