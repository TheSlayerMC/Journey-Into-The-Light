package net.journey.blocks.base;

import net.journey.JITL;
import net.journey.api.block.CustomItemModelProvider;
import net.journey.init.JourneyTabs;
import net.journey.util.StuffConstructor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

/**
 * Base class for wall blocks.
 * The item model for it should be placed to "models/item/block/wall/" by default.
 */
public class JBlockWall extends BlockWall implements CustomItemModelProvider {
    public JBlockWall(String name, String enName, Block modelBlock) {
        super(modelBlock);
        StuffConstructor.regAndSetupBlock(this, name, enName, JourneyTabs.BLOCKS);
    }

    @Override
    public @NotNull ResourceLocation getItemModelResourceLocation() {
        return new ResourceLocation(JITL.MOD_ID, "block/wall/" + getRegistryName().getPath());
    }

	/**
	 * Needs to disable adding mossy blocks in {@link BlockWall#getSubBlocks(CreativeTabs, NonNullList)}
	 */
	@Override
	public void getSubBlocks(@NotNull CreativeTabs itemIn, @NotNull NonNullList<ItemStack> items) {

	}
}
