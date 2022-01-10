package net.jitl.common.block;

import net.jitl.common.block.base.JFallingTileContainerBlock;
import net.jitl.common.tile.PotTile;
import net.jitl.init.JSounds;
import net.jitl.init.JTiles;
import net.jitl.util.JBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class AncientPotteryBlock extends JFallingTileContainerBlock {

    public AncientPotteryBlock() {
        super(JBlockProperties.POTTERY_PROPS.create().noOcclusion(), PotTile::new);
    }

   /* @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        VoxelShape middle = Block.box(1.0D, 14.0D, 1.0D, 15.0D, 2.0D, 15.0D);
        VoxelShape bottom = Block.box(2.0D, 2.0D, 2.0D, 14.0D, 0.0D, 14.0D);
        VoxelShape top = Block.box(2.0D, 2.0D, 2.0D, 14.0D, 16.0D, 14.0D);
        VoxelShape topMid = Block.box(5.0D, 1.0D, 5.0D, 11.0D, 18.0D, 11.0D);
        VoxelShape lip = Block.box(4.0D, 18.0D, 4.0D, 12.0D, 20.0D, 12.0D);

        return Shapes.or(middle, bottom, top, lip, topMid);
    }*/

    //TODO: "destroy" method only gets called when a player destroys the block.
    // need to find a solution so other causes of destruction, like explosions, also drop inventory contents

    @Override
    public void destroy(LevelAccessor worldIn, BlockPos pos, BlockState state) {
        BlockEntity tileentity = worldIn.getBlockEntity(pos);
        if (tileentity instanceof Container) {
            Containers.dropContents((Level) worldIn, pos, (Container) tileentity);
        }
    }

    /*@Override
    public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!isFree(state) && !isMoving) {
            if (!state.is(newState.getBlock())) {
                TileEntity tileentity = worldIn.getBlockEntity(pos);
                if (tileentity instanceof IInventory) {
                    InventoryHelper.dropContents(worldIn, pos, (IInventory) tileentity);
                    worldIn.updateNeighbourForOutputSignal(pos, this);
                }
                super.onRemove(state, worldIn, pos, newState, false);
            }
        }
    }*/

    @Override
    public void onLand(Level worldIn, BlockPos pos, BlockState fallingState, BlockState hitState, FallingBlockEntity fallingBlock) {
        fallingBlock.blockData.putBoolean("fallen", true);
    }

    @Override
    protected void falling(FallingBlockEntity fallingEntity) {
        BlockEntity tileEntity = fallingEntity.level.getBlockEntity(fallingEntity.blockPosition());
        if (tileEntity instanceof PotTile) {
            fallingEntity.blockData = tileEntity.save(new CompoundTag());
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
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
                        return InteractionResult.SUCCESS;
                    }
                    worldIn.playSound(null, pos, JSounds.BOTTLE_PLUG.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                }
            }
            return InteractionResult.sidedSuccess(worldIn.isClientSide);
        }
        return InteractionResult.sidedSuccess(worldIn.isClientSide);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level_, BlockState state_, BlockEntityType<T> blockEntityType_) {
        return createTicker(level_, blockEntityType_, JTiles.ANCIENT_POTTERY);
    }

    @Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level level_, BlockEntityType<T> givenType_, BlockEntityType<? extends PotTile> expectedType_) {
        return level_.isClientSide ? null : createTickerHelper(givenType_, expectedType_, PotTile::serverTick);
    }
}

