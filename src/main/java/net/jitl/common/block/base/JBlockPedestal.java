package net.jitl.common.block.base;

import net.jitl.common.tile.PedestalTile;
import net.jitl.core.init.JItems;
import net.jitl.core.util.JBlockProperties;
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
    private static final VoxelShape PEDESTAL = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 14.0D, 11.0D);
    private static final VoxelShape BOTTOM = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 2D, 12.0D);
    private static final VoxelShape TOP = Block.box(4.0D, 14.0D, 4.0D, 12.0D, 16D, 12.0D);
    private static final VoxelShape SHAPE = Shapes.or(PEDESTAL, BOTTOM, TOP);

    public JBlockPedestal() {
        super(JBlockProperties.STONE_PROPS.create(), PedestalTile::new);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
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

