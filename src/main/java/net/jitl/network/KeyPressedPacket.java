package net.jitl.network;

import net.jitl.capabilities.armorability.ArmorSetProvider;
import net.jitl.capabilities.armorability.IArmorSetCapability;
import net.jitl.common.item.gearabilities.CelestiumAbilities;
import net.jitl.common.item.gearabilities.IGearAbilities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.common.packet.ITimePacket;

import java.util.Optional;

public class KeyPressedPacket implements ITimePacket {

    private final double angle;

    @Override
    public @NotNull LogicalSide getReceptionSide() {
        return LogicalSide.SERVER;
    }

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
        public void onPacketReceived(KeyPressedPacket packet, NetworkEvent.Context ctx, World world) {
            LivingEntity entity = ctx.getSender();
            Optional<IArmorSetCapability> optional = entity.getCapability(ArmorSetProvider.ARMOR).resolve();
            if (optional.isPresent()) {
                IGearAbilities gear = optional.get().getArmor();
                if (gear instanceof CelestiumAbilities) {
                    ((CelestiumAbilities) gear).doCharge(entity, packet.angle);
                }
            }
            System.out.println("Angle: " + Math.toDegrees(packet.angle));
        }
    }
}