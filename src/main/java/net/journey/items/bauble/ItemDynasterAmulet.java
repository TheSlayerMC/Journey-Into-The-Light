package net.journey.items.bauble;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.journey.init.JourneyTabs;
import net.journey.items.base.JItem;
import net.journey.util.PotionEffects;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import java.util.List;

public class ItemDynasterAmulet extends JItem implements IBauble {

    public ItemDynasterAmulet(String name, String enName) {
        super(name, enName);
        setCreativeTab(JourneyTabs.UTIL);
        setMaxStackSize(1);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.AMULET;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        Potion potion = Potion.getPotionById(PotionEffects.blindness);
        if(player.isPotionActive(potion)) {
            player.removeActivePotionEffect(potion);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
        l.add(SlayerAPI.Colour.GOLD + "Negates Blindness When Worn");
    }
}
