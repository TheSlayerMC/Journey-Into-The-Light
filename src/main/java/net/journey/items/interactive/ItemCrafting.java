package net.journey.items.interactive;

import net.minecraft.block.BlockWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemCrafting extends Item {

    private final int type;

    public ItemCrafting(int type) {
        this.maxStackSize = 1;
        this.type = type;
        this.setMaxDamage(128);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
        ItemCrafting item = (ItemCrafting) player.getHeldItem(handIn).getItem();
        if (!world.isRemote) {
            if (!player.isSneaking()) {
                if (item.type == 0) {
                	player.getHeldItemMainhand().damageItem(1, player);
                    player.displayGui(new BlockWorkbench.InterfaceCraftingTable(world, player.getPosition()));
                }
            }
        }
        return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(handIn));
    }
}