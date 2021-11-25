package net.jitl.network;

import net.jitl.common.entity.base.IJourneyBoss;
import net.jitl.common.helper.JBossInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraftforge.fml.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.common.packet.ITimePacketHandler;
import ru.timeconqueror.timecore.api.util.Requirements;

import java.io.IOException;
import java.util.UUID;

public class JBossPacket {

    private Operation addOrRemove;
    private final UUID barUUID;
    private final Entity boss;

    public JBossPacket(Operation operation, UUID barUUID, Entity boss) {
        this.addOrRemove = operation;
        this.barUUID = barUUID;
        this.boss = boss;
    }

    public static class Handler implements ITimePacketHandler<JBossPacket> {

        @Override
        public void encode(JBossPacket packet, PacketBuffer buffer) throws IOException {
            buffer.writeEnum(packet.addOrRemove);
            buffer.writeUUID(packet.barUUID);
            buffer.writeInt(packet.boss.getId());
        }

        @Override
        public @NotNull JBossPacket decode(PacketBuffer buffer) throws IOException {
            return new JBossPacket(buffer.readEnum(Operation.class), buffer.readUUID(), Minecraft.getInstance().level.getEntity(buffer.readInt()));
        }

        @Override
        public boolean handle(JBossPacket packet, NetworkEvent.Context ctx) {
            ctx.enqueueWork(() -> {
                switch (packet.addOrRemove) {
                    case ADD:
                        if (packet.boss instanceof IJourneyBoss) {
                            JBossInfo.map.put(packet.barUUID, (IJourneyBoss) packet.boss);
                        } else {
                            throw new IllegalStateException("Attempted to add boss info to " + packet.boss.getClass().getName());
                        }
                        break;
                    case REMOVE:
                        JBossInfo.map.remove(packet.barUUID);
                        break;
                    default:
                        throw new IllegalStateException();
                }
            });

            return true;
        }
    }

    public enum Operation {
        ADD,
        REMOVE
    }
}
