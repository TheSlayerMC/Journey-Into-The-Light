package net.journey.client.render.gui;

import net.minecraft.entity.IMerchant;
import net.slayer.api.client.gui.GuiModVillager;
import net.slayer.api.entity.tileentity.container.ContainerModVillager;

public class GuiStaringGuardian extends GuiModVillager {

    public GuiStaringGuardian(ContainerModVillager container, IMerchant mer) {
        super(container, mer, "Staring Guardian", "staringGuardian", true);
    }
}