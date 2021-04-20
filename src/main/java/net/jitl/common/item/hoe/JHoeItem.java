package net.jitl.common.item.hoe;

import net.jitl.common.helper.JToolTiers;
import net.jitl.init.JTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class JHoeItem extends HoeItem {

	public JHoeItem(JToolTiers tier) {
		super(tier, 1, tier.getAttackSpeed(), new Item.Properties().tab(JTabs.TOOLS));
	}
}