package net.jitl.common.tile;

import net.jitl.core.init.JTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class BloodRuneSoulTile extends RandomizableContainerBlockEntity {
    private NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);

    public BloodRuneSoulTile(BlockPos pos, BlockState state) {
        super(JTiles.BLOOD_RUNE_SOUL, pos, state);
    }

    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> itemsIn) {
        this.inventory = itemsIn;
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, @NotNull Inventory player) {
        return null;
    }

    @Override
    public int getContainerSize() {
        return 1;
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt, this.inventory);
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag compound) {
        super.saveAdditional(compound);
        ContainerHelper.saveAllItems(compound, this.inventory);
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return new TranslatableComponent("jitl.tile.blood_rune_soul");
    }
}