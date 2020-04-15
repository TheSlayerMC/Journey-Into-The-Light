package net.journey.client.render.gui;

import net.minecraft.entity.IMerchant;
import net.slayer.api.client.gui.GuiModVillager;
import net.slayer.api.entity.tileentity.container.ContainerModVillager;

public class GuiTerranian extends GuiModVillager {

    public GuiTerranian(ContainerModVillager container, IMerchant mer) {
        super(container, mer, "Terranian Trader", "terranianTrader", true);
    }
}