package net.jitl.core.network;

import net.jitl.common.entity.base.IJourneyBoss;
import net.jitl.common.helper.JBossInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.common.packet.ITimePacketHandler;

import java.io.IOException;
import java.util.UUID;

public class SBossPacket {
    private final Operation addOrRemove;
    private final UUID barUUID;
    private final int bossNum;

    private SBossPacket(Operation operation, UUID barUUID, int bossNum) {
        this.addOrRemove = operation;
        this.barUUID = barUUID;
        this.bossNum = bossNum;
    }

    public SBossPacket(Operation operation, UUID barUUID, Entity boss) {
        this(operation, barUUID, boss.getId());
    }

    public static class Handler implements ITimePacketHandler<SBossPacket> {

        @Override
        public void encode(SBossPacket packet, FriendlyByteBuf buffer) throws IOException {
            buffer.writeEnum(packet.addOrRemove);
            buffer.writeUUID(packet.barUUID);
            buffer.writeInt(packet.bossNum);
        }

        @Override
        public @NotNull SBossPacket decode(FriendlyByteBuf buffer) throws IOException {
            return new SBossPacket(buffer.readEnum(Operation.class), buffer.readUUID(), buffer.readInt());
        }

        @Override
        public boolean handle(SBossPacket packet, NetworkEvent.Context ctx) {
            ctx.enqueueWork(() -> {
                switch (packet.addOrRemove) {
                    case ADD -> {
                        Entity boss = Minecraft.getInstance().level.getEntity(packet.bossNum);
                        if (boss instanceof IJourneyBoss) {
                            JBossInfo.map.put(packet.barUUID, (IJourneyBoss) boss);
                        } else {
                            throw new IllegalStateException("Attempted to add boss info to " + boss.getClass().getName());
                        }
                    }
                    case REMOVE -> JBossInfo.map.remove(packet.barUUID);
                    default -> throw new IllegalStateException();
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
