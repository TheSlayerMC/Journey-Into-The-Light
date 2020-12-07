package net.jitl.common.container;

import net.jitl.common.helper.JourneyContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;

public class ContainerEmpty extends Container {

    public ContainerEmpty() {
        super(JourneyContainers.EMPTY_CONTAINER, 200);
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return false;
    }

    public static ContainerEmpty createContainerClientSide(int windowID, PlayerInventory playerInventory, PacketBuffer extraData) {
        try {
            return new ContainerEmpty();
        } catch (IllegalArgumentException iae) {

        }
        return null;
    }
}
