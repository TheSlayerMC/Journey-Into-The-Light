package net.jitl.client.render.gui.base;

import net.jitl.core.init.client.JMenu;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeType;

public class JFurnaceMenu extends AbstractFurnaceMenu {

    public JFurnaceMenu(int sync, Inventory inventory) {
        super(JMenu.JFURNACE.get(), RecipeType.SMELTING, RecipeBookType.FURNACE, sync, inventory);
    }

    public JFurnaceMenu(int sync, Inventory inventory, Container container, ContainerData data) {
        super(JMenu.JFURNACE.get(), RecipeType.SMELTING, RecipeBookType.FURNACE, sync, inventory, container, data);
    }

}