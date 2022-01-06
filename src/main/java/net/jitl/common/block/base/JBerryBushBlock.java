package net.jitl.common.block.base;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.ItemLike;

public class JBerryBushBlock extends SweetBerryBushBlock implements BonemealableBlock {
	private final Supplier<ItemLike> berrySup;

	public JBerryBushBlock(Properties properties, Supplier<ItemLike> berrySup) {
		super(properties);
		this.berrySup = berrySup;
	}

	@Override
	public @NotNull ItemStack getCloneItemStack(@NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull BlockState state) {
		return new ItemStack(berrySup.get());
	}

	//copypaste except for berrySup.get()
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		int i = state.getValue(AGE);
		boolean flag = i == 3;
		if (!flag && player.getItemInHand(handIn).getItem() == Items.BONE_MEAL) {
			return InteractionResult.PASS;
		} else if (i > 1) {
			int j = 1 + worldIn.random.nextInt(2);
			popResource(worldIn, pos, new ItemStack(berrySup.get(), j + (flag ? 1 : 0)));
			worldIn.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			worldIn.setBlock(pos, state.setValue(AGE, 1), 2);
			return InteractionResult.sidedSuccess(worldIn.isClientSide);
		} else {
			return super.use(state, worldIn, pos, player, handIn, hit);
		}
	}
}
