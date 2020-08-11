package net.journey.items;

import net.journey.entity.util.EntityBossCrystal;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneyTabs;
import net.journey.items.base.JItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ItemTestBug extends JItem {
	public ItemTestBug(String name, String enName) {
		super(name, enName, JourneyTabs.UTIL);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (!worldIn.isRemote) {
			EntityBossCrystal bossCrystal = EntityBossCrystal.createFilledWith(((WorldServer) worldIn), playerIn.getPositionVector(), 0xFF00FF33, ((EntityPlayerMP) playerIn), JourneyLootTables.LOOT_DIAMOND, 0L);
			worldIn.spawnEntity(bossCrystal);
		}

		return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
}
