package net.jitl.network;

import net.jitl.common.capability.JCapabilityProvider;
import net.jitl.common.capability.pressedkeys.IPressedKeysCapability;
import net.jitl.common.eventhandler.CurioEventHandler;
import net.jitl.common.eventhandler.GearAbilityHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.common.packet.ITimePacketHandler;

import java.util.Optional;

public class KeyPressedPacket {

    private final boolean isAmulet;
    private final boolean isDown;

    public KeyPressedPacket(boolean key, boolean isDown) {
        isAmulet = key;
        this.isDown = isDown;
    }

    public static class Handler implements ITimePacketHandler<KeyPressedPacket> {
        @Override
        public void encode(KeyPressedPacket packet, FriendlyByteBuf buffer) {
            buffer.writeBoolean(packet.isAmulet);
            buffer.writeBoolean(packet.isDown);
        }

        @Override
        public @NotNull KeyPressedPacket decode(FriendlyByteBuf buffer) {
            return new KeyPressedPacket(buffer.readBoolean(), buffer.readBoolean());
        }

        @Override
        public boolean handle(KeyPressedPacket packet, NetworkEvent.Context ctx) {
            ctx.enqueueWork(() -> {
                ServerPlayer player = ctx.getSender();
                IPressedKeysCapability keys = JCapabilityProvider.getCapability(player, JCapabilityProvider.KEYS);
                if (keys != null) {
                    if (packet.isAmulet) {
                        keys.setAmuletPressed(packet.isDown);
                        CurioEventHandler.onKeyPressed(player);
                    } else {
                        keys.setArmorPressed(packet.isDown);
                        GearAbilityHandler.onKeyPressed(player);
                    }
                }
                System.out.println(player.getScoreboardName() + " " + (packet.isDown ? "pressed" : "released") + " " + (packet.isAmulet ? "amulet" : "armor") + " ability key.");
            });

            return true;
        }
    }
}