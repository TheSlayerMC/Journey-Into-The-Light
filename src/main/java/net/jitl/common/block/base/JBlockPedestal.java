package net.jitl.common.block.base;

import net.jitl.common.tile.PedestalTile;
import net.jitl.init.JItems;
import net.jitl.util.JBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class JBlockPedestal extends JTileContainerBlock {

    public JBlockPedestal() {
        super(JBlockProperties.STONE_PROPS.create(), (blockState, iBlockReader) -> new PedestalTile());
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        VoxelShape pedestal = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 20.0D, 11.0D);
        VoxelShape bottom = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 1D, 13.0D);
        VoxelShape bottom1 = Block.box(4.0D, 1.0D, 4.0D, 12.0D, 3D, 12.0D);
        VoxelShape top = Block.box(3.0D, 19.0D, 3.0D, 13.0D, 20D, 13.0D);
        VoxelShape top1 = Block.box(4.0D, 17.0D, 4.0D, 12.0D, 19D, 12.0D);
        VoxelShape top2 = Block.box(5.0D, 20.0D,5.0D, 11D, 21.0D, 11D);
        return Shapes.or(pedestal, bottom, bottom1, top, top1, top2);
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof Container) {
                Containers.dropContents(worldIn, pos, (Container) tileentity);
                worldIn.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        Item heldItem = player.getMainHandItem().getItem();
        if (worldIn.getBlockEntity(pos) instanceof PedestalTile) {
            PedestalTile pedestal = (PedestalTile) worldIn.getBlockEntity(pos);
            if (pedestal != null) {
                pedestal.getItem(0);
                if (!worldIn.isClientSide)
                    worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5F, pos.getY() + 1.4F, pos.getZ() + 0.5F, pedestal.getItem(0)));
                pedestal.setItem(0, ItemStack.EMPTY);
                if (heldItem == JItems.FROSTBORN_SOUL) {
                    pedestal.setItem(0, new ItemStack(heldItem));
                    worldIn.playSound(null, pos, SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                    if (!player.isCreative())
                        player.getMainHandItem().shrink(1);
                }
            }
        }
        return InteractionResult.sidedSuccess(worldIn.isClientSide);
    }
}

