package net.jitl.common.tile;

import net.jitl.core.init.JTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ClayVaseTile extends RandomizableContainerBlockEntity {
    private NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);
    private boolean hasFallen;

    public ClayVaseTile(BlockPos blockPos_, BlockState blockState_) {
        super(JTiles.CLAY_VASE, blockPos_, blockState_);
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
        return saveWithoutMetadata();
    }

    @Override
    public @NotNull void saveAdditional(@NotNull CompoundTag compound) {
        super.saveAdditional(compound);
        if (!this.trySaveLootTable(compound)) {
            ContainerHelper.saveAllItems(compound, this.inventory);
        }
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
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

    public static void serverTick(Level level, BlockPos pos, BlockState state, ClayVaseTile blockEntity) {
        if (level != null) {
            if (blockEntity.hasFallen) {
                Containers.dropContents(level, pos, blockEntity);
                level.destroyBlock(pos, true);
                blockEntity.setRemoved();
            }
        }
    }
}