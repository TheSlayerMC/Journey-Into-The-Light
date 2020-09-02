package net.slayer.api.entity.tileentity.container;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.world.World;

public class JContainerMerchant extends ContainerMerchant {

    public JContainerMerchant(InventoryPlayer playerInventory, IMerchant merchant, World worldIn) {
        super(playerInventory, merchant, worldIn);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}