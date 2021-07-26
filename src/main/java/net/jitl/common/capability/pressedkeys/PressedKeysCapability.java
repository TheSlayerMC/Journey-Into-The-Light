package net.jitl.common.capability.pressedkeys;

import net.jitl.client.eventhandler.KeybindEventHandler;
import net.jitl.common.capability.JCapabilityProvider;
import net.jitl.common.capability.armorability.IArmorSetCapability;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Optional;

public class PressedKeysCapability implements IPressedKeysCapability {
    private boolean armor;
    private boolean amulet;


    @Override
    public void setArmorPressed(boolean bool) {
        armor = bool;
    }

    @Override
    public boolean isArmorPressed() {
        return armor;
    }

    @Override
    public void setAmuletPressed(boolean bool) {
        amulet = bool;
    }

    @Override
    public boolean isAmuletPressed() {
        return amulet;
    }

    public static boolean isAmuletPressedEitherSide(PlayerEntity player) {
        if (KeybindEventHandler.keyAmulet != null) return KeybindEventHandler.keyAmulet.isDown();
        IPressedKeysCapability capability = JCapabilityProvider.getCapability(player, JCapabilityProvider.KEYS);
        return (capability != null && capability.isAmuletPressed());
    }
}
