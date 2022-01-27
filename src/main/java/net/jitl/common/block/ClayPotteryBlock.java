package net.jitl.common.block;

import net.jitl.common.block.base.JFallingTileContainerBlock;
import net.jitl.common.tile.ClayPotTile;
import net.jitl.core.init.JSounds;
import net.jitl.core.init.JTiles;
import net.jitl.core.util.JBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
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

public class ClayPotteryBlock extends JFallingTileContainerBlock {
    public static final VoxelShape MIDDLE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 12.0D, 15.0D);
    private static final VoxelShape FUNNEL = Block.box(5.0D, 1.0D, 5.0D, 11.0D, 14.0D, 11.0D);
    private static final VoxelShape LIP = Block.box(4.0D, 14.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    public static final VoxelShape SHAPE = Shapes.or(MIDDLE, LIP, FUNNEL);

    public ClayPotteryBlock() {
        super(JBlockProperties.POTTERY_PROPS.create().noOcclusion(), ClayPotTile::new);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState state_) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(@NotNull BlockState state, Level worldIn, BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
        boolean bottomIsSolid = !worldIn.getBlockState(pos.below()).isAir() || state.is(BlockTags.FIRE) || material.isLiquid() || material.isReplaceable();
        if (bottomIsSolid) {
            if (!state.is(newState.getBlock())) {
                BlockEntity tileentity = worldIn.getBlockEntity(pos);
                if (tileentity instanceof ClayPotTile ClayPotTile) {
                    CompoundTag tag = ClayPotTile.saveWithoutMetadata();
                    boolean hasFallen = tag.getBoolean("fallen");
                    if (!hasFallen) {
                        Containers.dropContents(worldIn, pos, ClayPotTile);
                    }
                }
            }
        }
        super.onRemove(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public void onLand(@NotNull Level worldIn, @NotNull BlockPos pos, @NotNull BlockState fallingState, @NotNull BlockState hitState, FallingBlockEntity fallingBlock) {
        CompoundTag blockData = fallingBlock.blockData;
        if (blockData != null)
            blockData.putBoolean("fallen", true);
    }

    @Override
    protected void falling(FallingBlockEntity fallingEntity) {
        fallingEntity.setHurtsEntities(2.0F, 20);
        BlockEntity tileEntity = fallingEntity.level.getBlockEntity(fallingEntity.blockPosition());
        if (tileEntity instanceof ClayPotTile) {
            fallingEntity.blockData = tileEntity.saveWithoutMetadata();
        }
    }

    @Override
    public InteractionResult use(@NotNull BlockState state, Level worldIn, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        ItemStack heldStack = player.getItemInHand(handIn);
        if (!worldIn.isClientSide()) {
            if (worldIn.getBlockEntity(pos) instanceof ClayPotTile ClayPotTile) {
                InvWrapper invWrapper = new InvWrapper(ClayPotTile);
                int slots = invWrapper.getSlots();
                if (!heldStack.isEmpty() && !player.isCrouching()) {
                    ItemStack stack = heldStack;
                    ItemStack prevStack = stack.copy();
                    for (int i = 0; i < slots; i++) {
                        if (!stack.isEmpty()) {
                            stack = invWrapper.insertItem(i, stack, false);
                        }
                    }
                    worldIn.playSound(null, pos, JSounds.BOTTLE_PLUG.get(), SoundSource.BLOCKS, 1.0F, Mth.nextFloat(player.getRandom(), 0.75F, 1.25F));
                    if (stack.isEmpty() || stack.getCount() != prevStack.getCount()) {
                        player.setItemInHand(handIn, stack);
                        return InteractionResult.SUCCESS;
                    }
                }
                if (player.isCrouching()) {
                    removeItemsFromContainer(worldIn, player, invWrapper, pos, slots, 64);
                } else {
                    removeItemsFromContainer(worldIn, player, invWrapper, pos, slots, 1);
                }
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.sidedSuccess(worldIn.isClientSide);
        }
        return InteractionResult.sidedSuccess(worldIn.isClientSide);
    }

    private void removeItemsFromContainer(Level worldIn, Player player, InvWrapper invWrapper, BlockPos pos, int slots, int amount) {
        for (int i = 0; i < slots; i++) {
            ItemStack extractItem = invWrapper.extractItem(i, amount, false);
            if (!extractItem.isEmpty()) {
                ItemEntity itemEntity = new ItemEntity(worldIn, player.getX(), player.getY(), player.getZ(), extractItem);
                worldIn.addFreshEntity(itemEntity);
                worldIn.playSound(null, pos, JSounds.BOTTLE_PLUG.get(), SoundSource.BLOCKS, 1.0F, Mth.nextFloat(player.getRandom(), 1.25F, 1.50F));
            }
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level_, BlockState state_, BlockEntityType<T> blockEntityType_) {
        return createTicker(level_, blockEntityType_, JTiles.CLAY_POTTERY);
    }

    @Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level level_, BlockEntityType<T> givenType_, BlockEntityType<? extends ClayPotTile> expectedType_) {
        return level_.isClientSide ? null : createTickerHelper(givenType_, expectedType_, ClayPotTile::serverTick);
    }
}

