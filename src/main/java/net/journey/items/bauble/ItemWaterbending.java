package net.journey.items.bauble;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.journey.client.server.EssenceProvider;
import net.journey.client.server.IEssence;
import net.journey.init.JourneyTabs;
import net.journey.items.base.JItem;
import net.journey.util.PotionEffects;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemWaterbending extends JItem implements IBauble {

	public ItemWaterbending(String name, String f) {
		super(name, f);
		setCreativeTab(JourneyTabs.UTIL);
		setMaxDamage(1);
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		ItemStack stack = player.getHeldItem(handIn);
		IEssence mana = player.getCapability(EssenceProvider.ESSENCE_CAP, null);		
		try {
			if(mana.useEssence(10)) {
				if(!world.isRemote) {
					player.addPotionEffect(PotionEffects.setPotionEffect(PotionEffects.waterBreathing, 2400, 1));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(handIn));
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemStack) {
		return BaubleType.CHARM;
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
		player.addPotionEffect(PotionEffects.setPotionEffect(PotionEffects.waterBreathing, 2400, 1));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
		l.add("Grants Waterbreathing");
	}
}