package net.jitl.common.tile;

import net.jitl.init.JTiles;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Containers;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.TickableBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import org.jetbrains.annotations.NotNull;

public class PotTile extends RandomizableContainerBlockEntity implements TickableBlockEntity {
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
    protected @NotNull AbstractContainerMenu createMenu(int id, @NotNull Inventory player) {
        return null;
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        return this.save(new CompoundTag());
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag compound) {
        super.save(compound);
        if (!this.trySaveLootTable(compound)) {
            ContainerHelper.saveAllItems(compound, this.inventory);
        }
        return compound;
    }

    @Override
    public void load(@NotNull BlockState state, @NotNull CompoundTag nbt) {
        super.load(state, nbt);
        this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(nbt)) {
            ContainerHelper.loadAllItems(nbt, this.inventory);
        }
        hasFallen = nbt.getBoolean("fallen");
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return new TranslatableComponent("jitl.tile.pot");
    }

    @Override
    public void tick() {
        if (hasFallen) {
            if (level != null) {
                Containers.dropContents(level, getBlockPos(), this);
                level.destroyBlock(getBlockPos(), true);
                setRemoved();
            }
        }
    }
}