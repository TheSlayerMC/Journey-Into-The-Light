package net.slayer.api.entity.tileentity.container;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.world.World;

public class ContainerModVillager extends ContainerMerchant {

    public ContainerModVillager(InventoryPlayer par1InventoryPlayer, IMerchant par2IMerchant, World par3World) {
        super(par1InventoryPlayer, par2IMerchant, par3World);
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return true;
    }
}