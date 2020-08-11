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
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		if (!world.isRemote) {
			EntityBossCrystal bossCrystal = EntityBossCrystal.create(((WorldServer) world), player.getPositionVector(), EntityBossCrystal.Type.EUCA, ((EntityPlayerMP) player), JourneyLootTables.LOOT_DIAMOND, 0L);
			world.spawnEntity(bossCrystal);
		}

		return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(handIn));
	}
}
