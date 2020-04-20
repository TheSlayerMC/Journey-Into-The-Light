package net.journey.items.interactive;

import net.journey.blocks.machines.BlockCrafting;
import net.journey.init.JourneyTabs;
import net.journey.init.items.JourneyItems;
import net.journey.util.LangRegistry;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;

public class ItemCrafting extends Item {

    private int type;

    public ItemCrafting(String name, String finalName, int type) {
        this(name, finalName, JourneyTabs.ITEMS);
        this.maxStackSize = 1;
        this.type = type;
        this.setMaxDamage(128);
    }

    public ItemCrafting(String name, String finalName, CreativeTabs tab) {
        LangRegistry.addItem(name, finalName);
        setTranslationKey(name);
        JourneyItems.items.add(this);
        JourneyItems.itemNames.add(SlayerAPI.PREFIX + name);
        setCreativeTab(JourneyTabs.UTIL);
        setRegistryName(SlayerAPI.MOD_ID, name);
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
        return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(handIn));
    }
}