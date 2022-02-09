package net.jitl.common.block;

import net.jitl.common.block.base.JTileContainerBlock;
import net.jitl.common.tile.BloodRuneSoulTile;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BloodRuneSoulBlock extends JTileContainerBlock {

    public BloodRuneSoulBlock(Properties properties) {
        super(properties, BloodRuneSoulTile::new);
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
        ItemStack heldItem = player.getMainHandItem();
        if (worldIn.getBlockEntity(pos) instanceof BloodRuneSoulTile rune) {
            rune.getItem(0);
            if (!worldIn.isClientSide)
                worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5F, pos.getY() + 1.4F, pos.getZ() + 0.5F, rune.getItem(0)));
            rune.setItem(0, ItemStack.EMPTY);
            if (!heldItem.isEmpty()) {
                rune.setItem(0, new ItemStack(heldItem.getItem(), 1));
                worldIn.playSound(null, pos, SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!player.isCreative())
                    player.getMainHandItem().shrink(1);
            }
        }
        return InteractionResult.sidedSuccess(worldIn.isClientSide);
    }
}