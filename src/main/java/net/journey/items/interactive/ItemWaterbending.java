package net.journey.items.interactive;

import java.util.List;

import net.journey.client.ItemDescription;
import net.journey.client.server.EssenceProvider;
import net.journey.client.server.IEssence;
import net.journey.entity.projectile.piercer.EntityEssenceShuriken;
import net.journey.init.JourneyTabs;
import net.journey.util.PotionEffects;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemMod;

public class ItemWaterbending extends ItemMod {

	public ItemWaterbending(String name, String f) {
		super(name, f);
		setCreativeTab(JourneyTabs.WEAPONS);
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
					stack.shrink(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(handIn));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
		l.add("Waterbreathing for 2 minutes");
		l.add("Uses 10 Essence");
	}
}