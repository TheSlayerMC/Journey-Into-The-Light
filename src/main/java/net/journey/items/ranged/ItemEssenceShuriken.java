package net.journey.items.ranged;

import net.journey.api.capability.EssenceStorage;
import net.journey.common.capability.JCapabilityManager;
import net.journey.entity.projectile.piercer.EntityEssenceShuriken;
import net.journey.items.base.JItem;
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

import java.util.List;

public class ItemEssenceShuriken extends JItem {

	public ItemEssenceShuriken() {
		setMaxDamage(20);
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		ItemStack stack = player.getHeldItem(handIn);
		EssenceStorage mana = JCapabilityManager.asJourneyPlayer(player).getEssenceStorage();
		try {
			if (mana.useEssence(3)) {
				if (!world.isRemote) {
					EntityEssenceShuriken t = new EntityEssenceShuriken(world, player, 5, 3);
					t.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
					world.spawnEntity(t);
					if (!player.capabilities.isCreativeMode) stack.damageItem(1, player);
					world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.MASTER, 1, 1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(handIn));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
		l.add("5 Ranged Damage");
		l.add("Uses 3 Essence");
		l.add("3 Max Bounces");
		l.add(i.getMaxDamage() - i.getItemDamage() + SlayerAPI.Colour.DARK_GREEN + " Uses remaining");
	}
}