package net.jitl.common.block;

import net.jitl.common.block.base.JTileContainerBlock;
import net.jitl.common.tile.PotTile;
import net.jitl.init.JSounds;
import net.jitl.util.JBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;

public class AncientPotteryBlock extends JTileContainerBlock {

    public AncientPotteryBlock() {
        super(JBlockProperties.POTTERY_PROPS.create().noOcclusion(), (blockState, iBlockReader) -> new PotTile());
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull IBlockReader worldIn, @NotNull BlockPos pos, @NotNull ISelectionContext context) {
        VoxelShape middle = Block.box(1.0D, 14.0D, 1.0D, 15.0D, 2.0D, 15.0D);
        VoxelShape bottom = Block.box(2.0D, 2.0D, 2.0D, 14.0D, 0.0D, 14.0D);
        VoxelShape top = Block.box(2.0D, 2.0D, 2.0D, 14.0D, 16.0D, 14.0D);
        VoxelShape lip = Block.box(4.0D, 1.0D, 4.0D, 12.0D, 20.0D, 12.0D);

        return VoxelShapes.or(middle, bottom, top, lip);
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

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide()) {
            if (worldIn.getBlockEntity(pos) instanceof PotTile) {
                PotTile potTile = (PotTile) worldIn.getBlockEntity(pos);
                InvWrapper invWrapper = new InvWrapper(potTile);
                ItemStack heldStack = player.getItemInHand(handIn);

                if (!heldStack.isEmpty()) {
                    ItemStack stack = heldStack;
                    ItemStack prevStack = stack.copy();
                    int slots = invWrapper.getSlots();
                    for (int i = 0; i < slots; i++) {
                        if (!stack.isEmpty()) {
                            stack = invWrapper.insertItem(i, stack, false);
                        }
                    }
                    if (stack.isEmpty() || stack.getCount() != prevStack.getCount()) {
                        player.setItemInHand(handIn, stack);
                        return ActionResultType.SUCCESS;
                    }
                    worldIn.playSound(null, pos, JSounds.BOTTLE_PLUG.get(), SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
            }
            return ActionResultType.sidedSuccess(worldIn.isClientSide);
        }
        return ActionResultType.sidedSuccess(worldIn.isClientSide);
    }
}

