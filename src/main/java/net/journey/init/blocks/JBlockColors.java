package net.journey.init.blocks;

import net.journey.JITL;
import net.journey.dimension.base.biome.EnumBiomeColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class JBlockColors implements IBlockColor, IItemColor {

	public static final IBlockColor BLOCK_COLOR_INSTANCE = new JBlockColors();
	public static final IItemColor ITEM_COLOR_INSTANCE = new JBlockColors();

	@Override
	public int colorMultiplier(@NotNull IBlockState iBlockState, @Nullable IBlockAccess iBlockAccess, @Nullable BlockPos blockPos, int i) {
		assert iBlockAccess != null && blockPos != null;
		return iBlockAccess.getBiome(blockPos).getGrassColorAtPos(blockPos);
	}

	@Override
	public int colorMultiplier(ItemStack itemStack, int i) {
		return EnumBiomeColor.CORBA_SWAMP.getInt();
	}

	public static void init() {
		JITL.LOGGER.info("Initializing Block Colors...");
		Minecraft minecraft = Minecraft.getMinecraft();
		BlockColors blockColors = minecraft.getBlockColors();
		blockColors.registerBlockColorHandler(BLOCK_COLOR_INSTANCE,
				JourneyBlocks.corbaGrass,
				JourneyBlocks.corbaTallGrass,
				JourneyBlocks.bogwoodLeaves,
				JourneyBlocks.corbaLeaves,

				JourneyBlocks.eucaGolditeGrass,
				JourneyBlocks.eucaGrass,
				JourneyBlocks.eucaSilverGrass
		);
		ItemColors itemColors = minecraft.getItemColors();
		itemColors.registerItemColorHandler(ITEM_COLOR_INSTANCE,
				JourneyBlocks.corbaGrass,
				JourneyBlocks.corbaTallGrass,
				JourneyBlocks.bogwoodLeaves,
				JourneyBlocks.corbaLeaves,

				JourneyBlocks.eucaGolditeGrass,
				JourneyBlocks.eucaGrass,
				JourneyBlocks.eucaSilverGrass
		);
	}
}
