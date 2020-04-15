package net.journey.client.render.gui;

import net.minecraft.entity.IMerchant;
import net.slayer.api.client.gui.GuiModVillager;
import net.slayer.api.entity.tileentity.container.ContainerModVillager;

public class GuiStarlightVillager extends GuiModVillager {

	public GuiStarlightVillager(ContainerModVillager container, IMerchant mer) { 
		super(container, mer, "Starlight Villager", "starlightVillager", true);
	}
}