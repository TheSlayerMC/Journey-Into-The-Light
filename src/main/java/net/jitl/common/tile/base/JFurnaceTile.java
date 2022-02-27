package net.jitl.common.tile.base;

import net.jitl.client.render.gui.base.JFurnaceMenu;
import net.jitl.core.init.JTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class JFurnaceTile extends AbstractFurnaceBlockEntity {

    public JFurnaceTile(BlockPos worldPosition_, BlockState blockState_) {
        super(JTiles.JFURNACE, worldPosition_, blockState_, RecipeType.SMELTING);
    }

    @Override
    protected Component getDefaultName() {
        return new TextComponent("container.furnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerID, Inventory inventory) {
        return new JFurnaceMenu(containerID, inventory, this, this.dataAccess);
    }
}
