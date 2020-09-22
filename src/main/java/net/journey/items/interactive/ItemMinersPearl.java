package net.journey.items.interactive;

import net.journey.JITL;
import net.journey.api.capability.EssenceStorage;
import net.journey.api.capability.JourneyPlayer;
import net.journey.api.item.IUsesEssence;
import net.journey.common.capability.JCapabilityManager;
import net.journey.init.JourneySounds;
import net.journey.items.base.JItem;
import net.journey.util.ChatUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.List;

public class ItemMinersPearl extends JItem implements IUsesEssence {

	private int teleportTimer;

	boolean canTeleport = false;

	public ItemMinersPearl() {
		setMaxDamage(16);
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer(player);
		EssenceStorage mana = journeyPlayer.getEssenceStorage();

		//make sure the player is below sea level, isn't in water and can't see the sky before attempting teleport
		boolean flag = player.posY <= world.getSeaLevel() - 2 &&
				!world.canSeeSky(new BlockPos(player.posX, player.posY + (double) player.getEyeHeight(), player.posZ)) &&
				!player.isInWater();

		try {
			if (flag) {
				if (mana.useEssence(10)) {
					if (world.isRemote) {
						world.playSound(player, player.getPosition(), SoundEvents.BLOCK_PORTAL_TRIGGER, SoundCategory.PLAYERS, 1.0F, 0.05F);
					}

					if (!world.isRemote) {
						player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200, 2));
						canTeleport = true;
						journeyPlayer.sendUpdates();
					}
				}
			} else if (!flag) {
				if (world.isRemote) {
					world.playSound(player, player.getPosition(), JourneySounds.STAFF, SoundCategory.PLAYERS, 1.0F, 0.05F);
				}

				if (!world.isRemote) {
					ChatUtils.sendColoredTranslated(player, TextFormatting.DARK_PURPLE, "msg.journey.item.miners_pearl");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(handIn));
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (canTeleport) {
			EntityPlayer player = (EntityPlayer) entity;
			teleportTimer++;
			if (teleportTimer >= 330) {
				player.attemptTeleport(player.posX, WorldGenAPI.findPosAboveSurface(world, player.getPosition().up()).getY(), player.posZ);
				teleportTimer = 0;
				canTeleport = false;
			}
			JITL.LOGGER.info("" + teleportTimer);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack i, World worldIn, List<String> l, ITooltipFlag flagIn) {
		l.add("Ascends user to the surface");
		l.add("Uses 10 Essence");
		l.add(i.getMaxDamage() - i.getItemDamage() + SlayerAPI.Colour.DARK_GREEN + " Uses remaining");
	}
}