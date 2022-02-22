package net.jitl.common.helper;

import net.jitl.common.entity.base.IJourneyBoss;
import net.jitl.core.network.JPacketHandler;
import net.jitl.core.network.SBossPacket;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashMap;
import java.util.UUID;

public class JBossInfo {
    public static HashMap<UUID, IJourneyBoss> map = new HashMap<>();

    public static void addInfo(ServerPlayer player, ServerBossEvent info, IJourneyBoss boss) {
        info.addPlayer(player);
        JPacketHandler.sendToPlayer(player, new SBossPacket(SBossPacket.Operation.ADD, info.getId(), (LivingEntity) boss));
    }

    public static void removeInfo(ServerPlayer player, ServerBossEvent info, IJourneyBoss boss) {
        info.removePlayer(player);
        JPacketHandler.sendToPlayer(player, new SBossPacket(SBossPacket.Operation.REMOVE, info.getId(), (LivingEntity) boss));
    }
}
