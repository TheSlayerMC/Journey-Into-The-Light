package net.jitl.client.eventhandler;

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
public class KnowledgeLevelEvent {

    private static final EntityType<?>[] OVERWORLD = {EntityType.AXOLOTL, EntityType.BAT, EntityType.BEE, EntityType.ILLUSIONER, EntityType.PILLAGER, EntityType.VINDICATOR, EntityType.CAT,
    EntityType.CAVE_SPIDER, EntityType.CHICKEN, EntityType.COW, EntityType.CREEPER, EntityType.DOLPHIN, EntityType.DONKEY, EntityType.DROWNED, EntityType.ELDER_GUARDIAN, EntityType.FOX,
    EntityType.GLOW_SQUID, EntityType.GOAT, EntityType.GUARDIAN, EntityType.LLAMA, EntityType.MOOSHROOM, EntityType.MULE, EntityType.OCELOT, EntityType.PANDA, EntityType.PARROT,
    EntityType.PIG, EntityType.PUFFERFISH, EntityType.RABBIT, EntityType.RAVAGER, EntityType.SALMON, EntityType.SHEEP, EntityType.SKELETON, EntityType.SILVERFISH, EntityType.SKELETON_HORSE,
    EntityType.SLIME, EntityType.SNOW_GOLEM, EntityType.SPIDER, EntityType.SQUID, EntityType.STRAY, EntityType.STRIDER, EntityType.TRADER_LLAMA, EntityType.TROPICAL_FISH,
    EntityType.TURTLE, EntityType.VILLAGER, EntityType.WANDERING_TRADER, EntityType.IRON_GOLEM, EntityType.WITCH, EntityType.WOLF, EntityType.WOLF, EntityType.ZOMBIE,
    EntityType.ZOMBIE_HORSE, EntityType.ZOMBIE_VILLAGER};//add these to overworld knowledge tag

    @SubscribeEvent
    public static void gainXP(LivingDeathEvent event) {
        Random r = new Random();
        float amount = r.nextFloat(1) + 0.5F;
        if(event.getSource().getEntity() instanceof Player p) {
            JPlayer player = JPlayer.from(p);
            EntityType<?> type = event.getEntityLiving().getType();
            Knowledge knowledge = player.knowledge;

            if(player != null && type != null) {
                if(type.is(JTags.OVERWORLD_MOBS)) {
                    knowledge.addXP(EnumKnowledgeType.OVERWORLD, amount);
                }

                if(type.is(JTags.NETHER_MOBS)) {
                    knowledge.addXP(EnumKnowledgeType.NETHER, amount);
                }

                if(type.is(JTags.END_MOBS)) {
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
            }
        }
    }
}