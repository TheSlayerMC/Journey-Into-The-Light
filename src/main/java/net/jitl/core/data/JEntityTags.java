package net.jitl.core.data;

import net.jitl.core.JITL;
import net.jitl.core.init.JEntities;
import net.jitl.core.init.JTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JEntityTags extends EntityTypeTagsProvider {

    public JEntityTags(DataGenerator generator_, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator_, JITL.MODID, existingFileHelper);
    }

    @Override
    public @NotNull String getName() {
        return JITL.rl("entity_tags").toString();
    }

    @Override
    protected void addTags() {
        addVanillaTags();
        addForgeTags();
        addOverworldTags();
        addNetherTags();
    }

    private void addVanillaTags() {
    }

    private void addForgeTags() {
    }

    private void addOverworldTags() {
        tag(JTags.OVERWORLD_MOBS).add(
                EntityType.AXOLOTL,
                EntityType.BAT,
                EntityType.BEE,
                EntityType.ILLUSIONER,
                EntityType.PILLAGER,
                EntityType.VINDICATOR,
                EntityType.CAT,
                EntityType.CAVE_SPIDER,
                EntityType.CHICKEN,
                EntityType.COW,
                EntityType.CREEPER,
                EntityType.DOLPHIN,
                EntityType.DONKEY,
                EntityType.DROWNED,
                EntityType.ELDER_GUARDIAN,
                EntityType.FOX,
                EntityType.GLOW_SQUID,
                EntityType.GOAT,
                EntityType.GUARDIAN,
                EntityType.LLAMA,
                EntityType.MOOSHROOM,
                EntityType.MULE,
                EntityType.OCELOT,
                EntityType.PANDA,
                EntityType.PARROT,
                EntityType.PIG,
                EntityType.PUFFERFISH,
                EntityType.RABBIT,
                EntityType.RAVAGER,
                EntityType.SALMON,
                EntityType.SHEEP,
                EntityType.SKELETON,
                EntityType.SILVERFISH,
                EntityType.SKELETON_HORSE,
                EntityType.SLIME,
                EntityType.SNOW_GOLEM,
                EntityType.SPIDER,
                EntityType.SQUID,
                EntityType.STRAY,
                EntityType.STRIDER,
                EntityType.TRADER_LLAMA,
                EntityType.TROPICAL_FISH,
                EntityType.TURTLE,
                EntityType.VILLAGER,
                EntityType.WANDERING_TRADER,
                EntityType.IRON_GOLEM,
                EntityType.WITCH,
                EntityType.WOLF,
                EntityType.WOLF,
                EntityType.ZOMBIE,
                EntityType.ZOMBIE_HORSE,
                EntityType.ZOMBIE_VILLAGER,

                JEntities.HONGLOW_TYPE,
                JEntities.HONGO_TYPE,
                JEntities.BROWN_HONGO_TYPE,
                JEntities.FLORO_TYPE,
                JEntities.TOWER_GUARDIAN_TYPE
        );
    }

    private void addNetherTags() {
        tag(JTags.NETHER_MOBS).add(
                EntityType.GHAST,
                EntityType.MAGMA_CUBE,
                EntityType.STRIDER,
                EntityType.WITHER_SKELETON,
                EntityType.PIGLIN,
                EntityType.ZOMBIFIED_PIGLIN,
                EntityType.PIGLIN_BRUTE,
                EntityType.HOGLIN,
                EntityType.BLAZE,

                JEntities.WITHERSPINE_TYPE,
                JEntities.MINI_GHAST_TYPE
        );
    }
}
