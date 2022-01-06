package net.jitl.common.tile;

import net.jitl.init.JTiles;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.jetbrains.annotations.NotNull;

public class PotTile extends LockableLootTileEntity implements ITickableTileEntity {
    private NonNullList<ItemStack> inventory = NonNullList.withSize(27, ItemStack.EMPTY);
    private boolean hasFallen;

    public PotTile() {
        super(JTiles.ANCIENT_POTTERY);
    }

    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> itemsIn) {
        inventory = itemsIn;
    }

    @Override
    protected @NotNull Container createMenu(int id, @NotNull PlayerInventory player) {
        return null;
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    public @NotNull CompoundNBT getUpdateTag() {
        return this.save(new CompoundNBT());
    }

    @Override
    public @NotNull CompoundNBT save(@NotNull CompoundNBT compound) {
        super.save(compound);
        if (!this.trySaveLootTable(compound)) {
            ItemStackHelper.saveAllItems(compound, this.inventory);
        }
        return compound;
    }

    @Override
    public void load(@NotNull BlockState state, @NotNull CompoundNBT nbt) {
        super.load(state, nbt);
        this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(nbt)) {
            ItemStackHelper.loadAllItems(nbt, this.inventory);
        }
        hasFallen = nbt.getBoolean("fallen");
    }

    @Override
    protected @NotNull ITextComponent getDefaultName() {
        return new TranslationTextComponent("jitl.tile.pot");
    }

    @Override
    public void tick() {
        if (hasFallen) {
            if (level != null) {
                InventoryHelper.dropContents(level, getBlockPos(), this);
                level.destroyBlock(getBlockPos(), true);
                setRemoved();
            }
        }
    }
}