package net.jitl.common.block.base;

import net.jitl.common.block.tileentity.PedestalTile;
import net.jitl.init.JItems;
import net.jitl.util.JBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class JBlockPedestal extends JTileContainerBlock {

    protected static final VoxelShape SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 20.0D, 13.0D);

    public JBlockPedestal() {
        super(JBlockProperties.STONE_PROPS.create(), (blockState, iBlockReader) -> new PedestalTile());
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull IBlockReader worldIn, @NotNull BlockPos pos, @NotNull ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        PedestalTile pedestal = (PedestalTile) worldIn.getBlockEntity(pos);
        Item heldItem = player.getMainHandItem().getItem();
        if (heldItem != null) {
            if (worldIn.getBlockEntity(pos) instanceof PedestalTile) {
                if (pedestal != null) {
                    pedestal.getItem(0);
                    if (!worldIn.isClientSide)
                        worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX() + 0.5F, pos.getY() + 1.4F, pos.getZ() + 0.5F, pedestal.getItem(0)));
                    pedestal.setItem(0, ItemStack.EMPTY);
                    if (heldItem == JItems.SAPPHIRE) {
                        pedestal.setItem(0, new ItemStack(heldItem));
                        worldIn.playSound(null, pos, SoundEvents.END_PORTAL_FRAME_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        if (!player.isCreative())
                            player.getMainHandItem().shrink(1);
                    }
                }
            }
        }
        return ActionResultType.sidedSuccess(worldIn.isClientSide);
    }
}

