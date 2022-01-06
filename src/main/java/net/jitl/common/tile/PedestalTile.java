package net.jitl.common.tile;

import net.jitl.init.JTiles;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import org.jetbrains.annotations.NotNull;

public class PedestalTile extends RandomizableContainerBlockEntity {
    private NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);

    public PedestalTile() {
        super(JTiles.PEDESTAL);
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
        return 1;
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        return this.save(new CompoundTag());
    }

    @Override
    public void load(@NotNull BlockState state, @NotNull CompoundTag nbt) {
        super.load(state, nbt);
        this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt, this.inventory);
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag compound) {
        super.save(compound);
        ContainerHelper.saveAllItems(compound, this.inventory);
        return compound;
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return new TranslatableComponent("jitl.tile.pedestal");
    }
}