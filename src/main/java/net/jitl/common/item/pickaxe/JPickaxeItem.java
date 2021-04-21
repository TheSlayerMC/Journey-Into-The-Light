package net.jitl.common.item.pickaxe;

import net.jitl.common.helper.JToolTiers;
import net.jitl.init.JTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class JPickaxeItem extends PickaxeItem {

	public JPickaxeItem(JToolTiers tier) {
		super(tier, (int) tier.getShovelDam(), tier.getAttackSpeed(), new Item.Properties().tab(JTabs.TOOLS));
	}
}