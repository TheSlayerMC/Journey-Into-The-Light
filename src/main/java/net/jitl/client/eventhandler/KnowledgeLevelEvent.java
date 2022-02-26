package net.jitl.client.eventhandler;

import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.capability.player.data.Knowledge;
import net.jitl.common.helper.EnumKnowledgeType;
import net.jitl.core.JITL;
import net.jitl.core.init.JEntities;
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
    EntityType.ZOMBIE_HORSE, EntityType.ZOMBIE_VILLAGER, JEntities.FLORO_TYPE, JEntities.HONGO_TYPE, JEntities.BROWN_HONGO_TYPE, JEntities.MINI_BOOM_TYPE, JEntities.WITHERSHROOM_TYPE,
            JEntities.HONGLOW_TYPE, JEntities.TOWER_GUARDIAN_TYPE, JEntities.GLUMP_TYPE, JEntities.ILLAGER_MECH_TYPE};

    private static final EntityType<?>[] NETHER = {EntityType.WITHER_SKELETON, EntityType.WITHER, EntityType.ZOMBIFIED_PIGLIN, EntityType.PIGLIN, EntityType.PIGLIN_BRUTE, EntityType.BLAZE,
            EntityType.GHAST, EntityType.MAGMA_CUBE, JEntities.WITHERSPINE_TYPE, JEntities.SOUL_WATCHER_TYPE, JEntities.MINI_GHAST_TYPE};

    private static final EntityType<?>[] END = {EntityType.ENDER_DRAGON, EntityType.ENDERMAN, EntityType.ENDERMITE, EntityType.END_CRYSTAL};

    private static final EntityType<?>[] EUCA = {JEntities.EUCA_HOPPER_TYPE, JEntities.GOLD_BOT_TYPE, JEntities.EUCA_CHARGER_TYPE, JEntities.DYNASTER_TYPE, JEntities.GOLDER_TYPE,
            JEntities.SHIMMERER_TYPE};

    private static final EntityType<?>[] FROZEN = {JEntities.ESKIMO_TYPE, JEntities.FROZEN_TROLL_TYPE, JEntities.PHANTASM_TYPE, JEntities.SHIVERING_RAM_TYPE, JEntities.CAPYBARA_TYPE,
            JEntities.SHATTERER_TYPE};

    @SubscribeEvent
    public static void gainXP(LivingDeathEvent event) {
        Random r = new Random();
        float amount = r.nextFloat(1) + 0.5F;
        if(event.getSource().getEntity() instanceof Player p) {
            JPlayer player = JPlayer.from(p);
            Knowledge knowledge = player.knowledge;
            if(player != null) {
               for(EntityType<?> type : OVERWORLD) {
                   if(type == event.getEntityLiving().getType()) {
                       knowledge.addXP(EnumKnowledgeType.OVERWORLD, amount);
                   }
                }

                for(EntityType<?> type : NETHER) {
                    if(type == event.getEntityLiving().getType()) {
                        knowledge.addXP(EnumKnowledgeType.NETHER, amount);
                    }
                }

                for(EntityType<?> type : END) {
                    if(type == event.getEntityLiving().getType()) {
                        knowledge.addXP(EnumKnowledgeType.END, amount);
                    }
                }

                for(EntityType<?> type : EUCA) {
                    if(type == event.getEntityLiving().getType()) {
                        knowledge.addXP(EnumKnowledgeType.EUCA, amount);
                    }
                }

                for(EntityType<?> type : FROZEN) {
                    if(type == event.getEntityLiving().getType()) {
                        knowledge.addXP(EnumKnowledgeType.FROZEN, amount);
                    }
                }
                player.detectAndSendChanges();
            }
        }
    }
}