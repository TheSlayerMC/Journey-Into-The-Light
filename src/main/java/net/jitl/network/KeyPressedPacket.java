package net.jitl.network;

import net.jitl.capabilities.JourneyCapabilityProvider;
import net.jitl.capabilities.armorability.IArmorSetCapability;
import net.jitl.common.item.gearabilities.BaseArmorAbilities;
import net.jitl.common.item.gearabilities.celestium.CelestiumArmorAbilities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.common.packet.ITimePacket;

import java.util.Optional;

public class KeyPressedPacket implements ITimePacket {

    private final double angle;

    public KeyPressedPacket(double rotation) {
        this.angle = rotation;
    }

    public static class Handler implements ITimePacketHandler<KeyPressedPacket> {
        @Override
        public void encode(KeyPressedPacket packet, PacketBuffer buffer) {
            buffer.writeDouble(packet.angle);
        }

        @Override
        public @NotNull KeyPressedPacket decode(PacketBuffer buffer) {
            return new KeyPressedPacket(buffer.readDouble());
        }

        @Override
        public boolean handle(KeyPressedPacket packet, NetworkEvent.Context ctx) {
            ctx.enqueueWork(() -> {
                LivingEntity entity = ctx.getSender();
                Optional<IArmorSetCapability> optional = entity.getCapability(JourneyCapabilityProvider.ARMOR).resolve();
                if (optional.isPresent()) {
                    BaseArmorAbilities gear = optional.get().getArmor();
                    if (gear instanceof CelestiumArmorAbilities) {
                        ((CelestiumArmorAbilities) gear).doCharge(entity, packet.angle);
                    }
                }
                System.out.println("Angle: " + Math.toDegrees(packet.angle));
            });

            return true;
        }
    }
}