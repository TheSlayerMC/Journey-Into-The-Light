package net.jitl.common.container;

import net.jitl.common.helper.JourneyContainers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.network.FriendlyByteBuf;

public class ContainerEmpty extends AbstractContainerMenu {

    public ContainerEmpty() {
        super(JourneyContainers.EMPTY_CONTAINER, 200);
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return false;
    }

    public static ContainerEmpty createContainerClientSide(int windowID, Inventory playerInventory, FriendlyByteBuf extraData) {
        try {
            return new ContainerEmpty();
        } catch (IllegalArgumentException iae) {

        }
        return null;
    }
}
