package net.jitl.client.render.screen;

import net.jitl.client.render.gui.base.JFurnaceMenu;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class JFurnaceScreen extends AbstractFurnaceScreen<JFurnaceMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/furnace.png");

    public JFurnaceScreen(JFurnaceMenu menu_, Inventory playerInventory_, Component title_) {
        super(menu_, new SmeltingRecipeBookComponent(), playerInventory_, title_, TEXTURE);
    }
}
