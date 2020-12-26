package net.jitl.common.block.base;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.function.Supplier;

public class JBerryBushBlock extends BushBlock implements IGrowable {
	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
	private static final VoxelShape SAPLING_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
	private static final VoxelShape MID_GROWTH_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
	private final Supplier<IItemProvider> berry;

	public JBerryBushBlock(AbstractBlock.Properties properties, Supplier<IItemProvider> berry) {
		super(properties);
		this.berry = berry;
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
	}

	@Override
	public @NotNull ItemStack getCloneItemStack(@NotNull IBlockReader worldIn, @NotNull BlockPos pos, @NotNull BlockState state) {
		return new ItemStack(berry.get());
	}

	@Override
	public @NotNull VoxelShape getShape(BlockState state, @NotNull IBlockReader worldIn, @NotNull BlockPos pos, @NotNull ISelectionContext context) {
		if (state.getValue(AGE) == 0) {
			return SAPLING_SHAPE;
		} else {
			return state.getValue(AGE) < 3 ? MID_GROWTH_SHAPE : super.getShape(state, worldIn, pos, context);
		}
	}

	/**
	 * Returns whether or not this block is of a type that needs random ticking. Called for ref-counting purposes by
	 * ExtendedBlockStorage in order to broadly cull a chunk from the random chunk update list for efficiency's sake.
	 */
	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return state.getValue(AGE) < 3;
	}

	/**
	 * Performs a random tick on a block.
	 */
	@Override
	public void randomTick(BlockState state, @NotNull ServerWorld worldIn, @NotNull BlockPos pos, @NotNull Random random) {
		int i = state.getValue(AGE);
		if (i < 3 && worldIn.getRawBrightness(pos.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(5) == 0)) {
			worldIn.setBlock(pos, state.setValue(AGE, i + 1), 2);
			ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}

	@Override
	public void entityInside(@NotNull BlockState state, @NotNull World worldIn, @NotNull BlockPos pos, @NotNull Entity entityIn) {
		if (entityIn instanceof LivingEntity && entityIn.getType() != EntityType.FOX && entityIn.getType() != EntityType.BEE) {
			entityIn.makeStuckInBlock(state, new Vector3d(0.8F, 0.75D, 0.8F));
			if (!worldIn.isClientSide && state.getValue(AGE) > 0 && (entityIn.xOld != entityIn.getX() || entityIn.zOld != entityIn.getZ())) {
				double d0 = Math.abs(entityIn.getX() - entityIn.xOld);
				double d1 = Math.abs(entityIn.getZ() - entityIn.zOld);
				if (d0 >= (double) 0.003F || d1 >= (double) 0.003F) {
					entityIn.hurt(DamageSource.SWEET_BERRY_BUSH, 1.0F);
				}
			}
		}
	}

	@Override
	public @NotNull ActionResultType use(BlockState state, @NotNull World worldIn, @NotNull BlockPos pos, @NotNull PlayerEntity player, @NotNull Hand handIn, @NotNull BlockRayTraceResult hit) {
		int i = state.getValue(AGE);
		boolean flag = i == 3;
		if (!flag && player.getItemInHand(handIn).getItem() == Items.BONE_MEAL) {
			return ActionResultType.PASS;
		} else if (i > 1) {
			int j = 1 + worldIn.random.nextInt(2);
			popResource(worldIn, pos, new ItemStack(berry.get(), j + (flag ? 1 : 0)));
			worldIn.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			worldIn.setBlock(pos, state.setValue(AGE, 1), 2);
			return ActionResultType.sidedSuccess(worldIn.isClientSide);
		} else {
			return super.use(state, worldIn, pos, player, handIn, hit);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	/**
	 * Whether this IGrowable can grow
	 */
	@Override
	public boolean isValidBonemealTarget(@NotNull IBlockReader worldIn, @NotNull BlockPos pos, BlockState state, boolean isClient) {
		return state.getValue(AGE) < 3;
	}

	@Override
	public boolean isBonemealSuccess(@NotNull World worldIn, @NotNull Random rand, @NotNull BlockPos pos, @NotNull BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerWorld worldIn, @NotNull Random rand, @NotNull BlockPos pos, BlockState state) {
		int i = Math.min(3, state.getValue(AGE) + 1);
		worldIn.setBlock(pos, state.setValue(AGE, i), 2);
	}
}
