package net.journey.client.render.gui;

import net.minecraft.entity.IMerchant;
import net.slayer.api.client.gui.GuiModVillager;
import net.slayer.api.entity.tileentity.container.ContainerModVillager;

public class GuiFrozenMerchant extends GuiModVillager {

	public GuiFrozenMerchant(ContainerModVillager container, IMerchant mer) {
		super(container, mer, "Frozen Merchant", "frozenMerchant");
	}
}