package net.jitl.common.block;

import net.jitl.common.block.base.JTileContainerBlock;
import net.jitl.common.tile.PedestalTile;
import net.jitl.util.JBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class AncientPotteryBlock extends JTileContainerBlock {

    protected static final VoxelShape SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 20.0D, 13.0D);

    public AncientPotteryBlock() {
        super(JBlockProperties.BRICK_PROPS.create().noOcclusion(), (blockState, iBlockReader) -> new PedestalTile());
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull IBlockReader worldIn, @NotNull BlockPos pos, @NotNull ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            TileEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof IInventory) {
                InventoryHelper.dropContents(worldIn, pos, (IInventory) tileentity);
                worldIn.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    /*@Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        Item heldItem = player.getMainHandItem().getItem();
        if (worldIn.getBlockEntity(pos) instanceof PedestalTile) {
            PedestalTile pedestal = (PedestalTile) worldIn.getBlockEntity(pos);
            if (pedestal != null) {
                pedestal.setItem(0, new ItemStack(heldItem));
                worldIn.playSound(null, pos, SoundEvents.END_PORTAL_FRAME_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!player.isCreative())
                    player.getMainHandItem().shrink(1);
            }
        }
        return ActionResultType.sidedSuccess(worldIn.isClientSide);
    }*/
}

