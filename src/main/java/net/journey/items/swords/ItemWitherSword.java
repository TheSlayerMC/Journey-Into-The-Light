package net.journey.items.swords;

import net.journey.util.JourneyToolMaterial;
import net.journey.util.LangHelper;
import net.journey.util.PotionEffects;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemModSword;

import java.util.List;

public class ItemWitherSword extends ItemModSword {

    public ItemWitherSword(String name, String f, JourneyToolMaterial toolMaterial) {
        super(name, f, toolMaterial);
    }

    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase hit, EntityLivingBase player) {
        hit.addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.wither, 100, 5)));
        return super.hitEntity(par1ItemStack, hit, player);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, World player, List<String> infoList, ITooltipFlag par4) {
        infoList.add(SlayerAPI.Colour.DARK_GRAY + LangHelper.setWitherSword(6));
        if (item.getMaxDamage() != -1)
            infoList.add(item.getMaxDamage() - item.getItemDamage() + " " + LangHelper.getUsesRemaining());
        else infoList.add(SlayerAPI.Colour.GREEN + LangHelper.getInfiniteUses());
    }


}