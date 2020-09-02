package net.journey.items.bauble;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.journey.init.JourneyTabs;
import net.journey.items.base.JItem;
import net.journey.util.PotionEffects;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemWaterbending extends JItem implements IBauble {

	public ItemWaterbending(String name, String f) {
		super(name, f);
		setCreativeTab(JourneyTabs.UTIL);
		setMaxStackSize(1);
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemStack) {
		return BaubleType.CHARM;
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
		player.addPotionEffect(PotionEffects.setPotionEffect(PotionEffects.waterBreathing, 10, 1));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
		l.add("Grants Waterbreathing");
	}
}