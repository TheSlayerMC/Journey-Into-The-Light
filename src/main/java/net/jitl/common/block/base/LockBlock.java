package net.jitl.common.block.base;

import net.jitl.init.JBlocks;
import net.jitl.init.JItems;
import net.jitl.init.JSounds;
import net.jitl.util.JBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import java.util.Random;

public class LockBlock extends Block {

    public static final DirectionProperty FACING = HorizontalBlock.FACING;

    public LockBlock() {
        super(JBlockProperties.STONE_PROPS.create());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack heldItem = player.getMainHandItem();
        Random r = new Random();
        boolean canOpen = false;
        if(this == JBlocks.BOIL_LOCK && heldItem.getItem() == JItems.BOIL_KEY)
            canOpen = true;

        if(heldItem != null && canOpen) {
            worldIn.playSound(player, pos, SoundEvents.IRON_DOOR_OPEN, SoundCategory.BLOCKS, 1.0F, r.nextFloat());
            if(worldIn.isClientSide) {
                return ActionResultType.SUCCESS;
            } else {
                if(state.getValue(FACING) == Direction.WEST || state.getValue(FACING) == Direction.EAST) {
                    for(int y = -1; y < 2; y++) {
                        for(int z = -1; z < 2; z++) {
                            worldIn.removeBlock(pos.offset(0, y, z), false);
                        }
                    }
                }
                if(state.getValue(FACING) == Direction.NORTH || state.getValue(FACING) == Direction.SOUTH) {
                    for (int x = -1; x < 2; x++) {
                        for (int y = -1; y < 2; y++) {
                            worldIn.removeBlock(pos.offset(x, y, 0), false);
                        }
                    }
                }
                if(!player.isCreative())
                    heldItem.shrink(1);
            }
        }
        return ActionResultType.CONSUME;
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
