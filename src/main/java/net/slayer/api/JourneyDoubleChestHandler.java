package net.slayer.api;

import com.google.common.base.Objects;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.ref.WeakReference;

public class JourneyDoubleChestHandler extends WeakReference<TileEntityJourneyChest> implements IItemHandlerModifiable {
    public static final JourneyDoubleChestHandler NO_ADJACENT_CHESTS_INSTANCE = new JourneyDoubleChestHandler(null, null, false);
    private final boolean mainChestIsUpper;
    private final TileEntityJourneyChest mainChest;
    private final int hashCode;

    public JourneyDoubleChestHandler(@Nullable TileEntityJourneyChest mainChest, @Nullable TileEntityJourneyChest other, boolean mainChestIsUpper) {
        super(other);
        this.mainChest = mainChest;
        this.mainChestIsUpper = mainChestIsUpper;
        hashCode = Objects.hashCode(mainChestIsUpper ? mainChest : other) * 31 + Objects.hashCode(!mainChestIsUpper ? mainChest : other);
    }

    @Nullable
    public static JourneyDoubleChestHandler get(TileEntityJourneyChest chest) {
        World world = chest.getWorld();
        BlockPos pos = chest.getPos();
        if (world == null || pos == null || !world.isBlockLoaded(pos))
            return null;

        Block blockType = chest.getBlockType();

        EnumFacing[] horizontals = EnumFacing.HORIZONTALS;
        for (int i = horizontals.length - 1; i >= 0; i--) {
            EnumFacing enumfacing = horizontals[i];
            BlockPos blockpos = pos.offset(enumfacing);
            Block block = world.getBlockState(blockpos).getBlock();

            if (block == blockType) {
                TileEntity otherTE = world.getTileEntity(blockpos);

                if (otherTE instanceof TileEntityJourneyChest) {
                    TileEntityJourneyChest otherChest = (TileEntityJourneyChest) otherTE;
                    return new JourneyDoubleChestHandler(chest, otherChest,
                            enumfacing != net.minecraft.util.EnumFacing.WEST && enumfacing != net.minecraft.util.EnumFacing.NORTH);

                }
            }
        }
        return NO_ADJACENT_CHESTS_INSTANCE; //All alone
    }

    @Nullable
    public TileEntityJourneyChest getChest(boolean accessingUpper) {
        if (accessingUpper == mainChestIsUpper)
            return mainChest;
        else {
            return getOtherChest();
        }
    }

    @Nullable
    private TileEntityJourneyChest getOtherChest() {
        TileEntityJourneyChest journeyChest = get();
        return journeyChest != null && !journeyChest.isInvalid() ? journeyChest : null;
    }

    @Override
    public int getSlots() {
        return 27 * 2;
    }

    @Override
    @Nonnull
    public ItemStack getStackInSlot(int slot) {
        boolean accessingUpperChest = slot < 27;
        int targetSlot = accessingUpperChest ? slot : slot - 27;
        TileEntityJourneyChest chest = getChest(accessingUpperChest);
        return chest != null ? chest.getStackInSlot(targetSlot) : ItemStack.EMPTY;
    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
        boolean accessingUpperChest = slot < 27;
        int targetSlot = accessingUpperChest ? slot : slot - 27;
        TileEntityJourneyChest chest = getChest(accessingUpperChest);
        if (chest != null) {
            IItemHandler singleHandler = chest.getSingleChestHandler();
            if (singleHandler instanceof IItemHandlerModifiable) {
                ((IItemHandlerModifiable) singleHandler).setStackInSlot(targetSlot, stack);
            }
        }

        chest = getChest(!accessingUpperChest);
        if (chest != null)
            chest.markDirty();
    }

    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        boolean accessingUpperChest = slot < 27;
        int targetSlot = accessingUpperChest ? slot : slot - 27;
        TileEntityJourneyChest chest = getChest(accessingUpperChest);
        if (chest == null)
            return stack;

        int starting = stack.getCount();
        ItemStack ret = chest.getSingleChestHandler().insertItem(targetSlot, stack, simulate);
        if (ret.getCount() != starting && !simulate) {
            chest = getChest(!accessingUpperChest);
            if (chest != null)
                chest.markDirty();
        }

        return ret;
    }

    @Override
    @Nonnull
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        boolean accessingUpperChest = slot < 27;
        int targetSlot = accessingUpperChest ? slot : slot - 27;
        TileEntityJourneyChest chest = getChest(accessingUpperChest);
        if (chest == null)
            return ItemStack.EMPTY;

        ItemStack ret = chest.getSingleChestHandler().extractItem(targetSlot, amount, simulate);
        if (!ret.isEmpty() && !simulate) {
            chest = getChest(!accessingUpperChest);
            if (chest != null)
                chest.markDirty();
        }

        return ret;
    }

    @Override
    public int getSlotLimit(int slot) {
        boolean accessingUpperChest = slot < 27;
        return getChest(accessingUpperChest).getInventoryStackLimit();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        JourneyDoubleChestHandler that = (JourneyDoubleChestHandler) o;

        if (hashCode != that.hashCode)
            return false;

        final TileEntityJourneyChest otherChest = getOtherChest();
        if (mainChestIsUpper == that.mainChestIsUpper)
            return Objects.equal(mainChest, that.mainChest) && Objects.equal(otherChest, that.getOtherChest());
        else
            return Objects.equal(mainChest, that.getOtherChest()) && Objects.equal(otherChest, that.mainChest);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    public boolean needsRefresh() {
        if (this == NO_ADJACENT_CHESTS_INSTANCE)
            return false;
        TileEntityJourneyChest journeyChest = get();
        return journeyChest == null || journeyChest.isInvalid();
    }
}