package net.jitl.common.block.base;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class JBerryBushBlock extends SweetBerryBushBlock implements IGrowable {
	private final Supplier<IItemProvider> berrySup;

	public JBerryBushBlock(AbstractBlock.Properties properties, Supplier<IItemProvider> berrySup) {
		super(properties);
		this.berrySup = berrySup;
	}

	@Override
	public @NotNull ItemStack getCloneItemStack(@NotNull IBlockReader worldIn, @NotNull BlockPos pos, @NotNull BlockState state) {
		return new ItemStack(berrySup.get());
	}
}
