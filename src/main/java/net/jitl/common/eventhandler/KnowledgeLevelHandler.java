package net.jitl.common.eventhandler;

import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.capability.player.data.Knowledge;
import net.jitl.common.helper.EnumKnowledgeType;
import net.jitl.core.JITL;
import net.jitl.core.init.JTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class KnowledgeLevelHandler {

    @SubscribeEvent
    public static void gainXP(LivingDeathEvent event) {
        Random r = new Random();
        float amount = r.nextFloat(1) + 0.5F;
        if (event.getSource().getEntity() instanceof Player p) {
            JPlayer player = JPlayer.from(p);
            EntityType<?> type = event.getEntityLiving().getType();

            if (player != null) {
                Knowledge knowledge = player.knowledge;
                if (type.is(JTags.OVERWORLD_MOBS)) {
                    JITL.LOGGER.info("Added overworld knowledge from {}, with amount {}", type, amount);
                    knowledge.addXP(EnumKnowledgeType.OVERWORLD, amount);
                }

                if (type.is(JTags.NETHER_MOBS)) {
                    knowledge.addXP(EnumKnowledgeType.NETHER, amount);
                }

                if (type.is(JTags.END_MOBS)) {
                    knowledge.addXP(EnumKnowledgeType.END, amount);
                }

                if(type.is(JTags.EUCA_MOBS)) {
                    knowledge.addXP(EnumKnowledgeType.EUCA, amount);
                }

                if(type.is(JTags.FROZEN_MOBS)) {
                    knowledge.addXP(EnumKnowledgeType.FROZEN, amount);
                }

                if(type.is(JTags.BOIL_MOBS)) {
                    knowledge.addXP(EnumKnowledgeType.BOIL, amount);
                }
                player.detectAndSendChanges();
                JITL.LOGGER.info(player.knowledge.getLevel(EnumKnowledgeType.FROZEN));

            }
        }
    }
}