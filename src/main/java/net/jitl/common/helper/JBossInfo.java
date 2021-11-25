package net.jitl.common.helper;

import net.jitl.common.entity.base.IJourneyBoss;
import net.jitl.network.JBossPacket;
import net.jitl.network.JPacketHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.UUID;

public class JBossInfo {
    public static HashMap<UUID, IJourneyBoss> map = new HashMap<UUID, IJourneyBoss>(); //TODO: maybe use something other than HashMap?

    public static void addInfo(ServerPlayerEntity player, ServerBossInfo info, IJourneyBoss boss) {
        info.addPlayer(player);
        JPacketHandler.sendToPlayer(player, new JBossPacket(JBossPacket.Operation.ADD, info.getId(), (LivingEntity) boss));
    }

    public static void removeInfo(ServerPlayerEntity player, ServerBossInfo info, IJourneyBoss boss) {
        info.removePlayer(player);
        JPacketHandler.sendToPlayer(player, new JBossPacket(JBossPacket.Operation.REMOVE, info.getId(), (LivingEntity) boss));
    }
}
