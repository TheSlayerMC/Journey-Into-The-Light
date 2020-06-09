package net.journey.blocks.base;

import net.journey.JITL;
import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.gen.lang.LangGeneratorFacade;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class JBlockSlab extends BlockSlab {

	public String name;
    boolean isDouble;
	
	public JBlockSlab(Material modelBlock, String name, String enName, boolean isDouble) {
		super(modelBlock);
        this.name = name;
        this.isDouble = isDouble;
        setTranslationKey(name);
        setCreativeTab(JourneyTabs.BLOCKS);
        setHardness(2.0F);
        JourneyBlocks.blocks.add(this);
        setRegistryName(JITL.MOD_ID, name);
        LangGeneratorFacade.addBlockEntry(this, enName);
        JourneyBlocks.itemBlocks.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		if (this.getRegistryName().toString().contains("double")) {
			return new ItemStack(this, 2, 0);
		} else {
			return new ItemStack(this, 1, 0);
		}
	}

	@Override
	public String getTranslationKey(int meta) {
		return this.name;
	}

	@Override
	public boolean isDouble() {
		return this.isDouble;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		if (!this.isDouble()) {
			return this.getDefaultState().withProperty(HALF, meta == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
		}

		return this.getDefaultState();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		if (!this.isDouble()) {
			if (state.getValue(HALF) == BlockSlab.EnumBlockHalf.BOTTOM) {
				return 0;
			} else if (state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return this.isDouble() ? new BlockStateContainer(this) : new BlockStateContainer(this, HALF);
	}
	
	@Override
	public IProperty<?> getVariantProperty() {
		return null;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return null;
	}
}
