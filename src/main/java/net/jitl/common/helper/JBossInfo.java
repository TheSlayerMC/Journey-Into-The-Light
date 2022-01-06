package net.jitl.common.helper;

import net.jitl.client.render.gui.BossBarRenderer;
import net.jitl.common.entity.base.IJourneyBoss;
import net.jitl.network.JBossPacket;
import net.jitl.network.JPacketHandler;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.UUID;

public class JBossInfo {
    public static HashMap<UUID, IJourneyBoss> map = new HashMap<UUID, IJourneyBoss>(); //TODO: maybe use something other than HashMap?

    public static void addInfo(ServerPlayer player, ServerBossEvent info, IJourneyBoss boss) {
        info.addPlayer(player);
        JPacketHandler.sendToPlayer(player, new JBossPacket(JBossPacket.Operation.ADD, info.getId(), (LivingEntity) boss));
    }

    public static void removeInfo(ServerPlayer player, ServerBossEvent info, IJourneyBoss boss) {
        info.removePlayer(player);
        JPacketHandler.sendToPlayer(player, new JBossPacket(JBossPacket.Operation.REMOVE, info.getId(), (LivingEntity) boss));
    }
}
