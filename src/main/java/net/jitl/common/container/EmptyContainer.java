package net.jitl.common.container;

import net.jitl.common.helper.JContainers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class EmptyContainer extends AbstractContainerMenu {

    public EmptyContainer() {
        super(JContainers.EMPTY_CONTAINER, 200);
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return false;
    }

    public static EmptyContainer createContainerClientSide(int windowID, Inventory playerInventory, FriendlyByteBuf extraData) {
        try {
            return new EmptyContainer();
        } catch (IllegalArgumentException iae) {

        }
        return null;
    }
}
