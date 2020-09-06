package net.journey.items;

import net.journey.api.capability.PlayerStats;
import net.journey.common.capability.JCapabilityManager;
import net.journey.common.knowledge.EnumKnowledgeType;
import net.journey.init.JourneyTabs;
import net.journey.items.base.JItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemTestBug extends JItem {
	public ItemTestBug(String name, String enName) {
		super(name, enName, JourneyTabs.UTIL);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		if (!world.isRemote) {
			JCapabilityManager.asJourneyPlayer(player).getPlayerStats().addKnowledge(EnumKnowledgeType.TERRANIA, 11);

			PlayerStats.KnowledgeStorage knowledge = JCapabilityManager.asJourneyPlayer(player).getPlayerStats().getKnowledge(EnumKnowledgeType.TERRANIA);
			System.out.println("knowledge.getLevelCount() = " + knowledge.getLevelCount());
			System.out.println("knowledge.getAmountOnCurrentLevel() = " + knowledge.getAmountOnCurrentLevel());

			JCapabilityManager.asJourneyPlayer(player).sendUpdates(((EntityPlayerMP) player));
		}

		return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(handIn));
	}
}
