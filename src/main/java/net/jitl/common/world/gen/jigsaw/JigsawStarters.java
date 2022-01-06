package net.jitl.common.world.gen.jigsaw;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.jitl.JITL;
import net.jitl.init.JProcessorLists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElement;

import StructureTemplatePool;

public class JigsawStarters {
    //TODO: fix processor
    public static final StructureTemplatePool ESKIMO_CAMP_START = Pools.register(
            new StructureTemplatePool(JITL.rl("frozen/eskimo_camp/starting_well"),
                    new ResourceLocation("empty"),
                    ImmutableList.of(
                            Pair.of(StructurePoolElement.single(JITL.MODID + ":frozen/eskimo_camp/eskimo_starting_well", JProcessorLists.IGLOO_PROCESSOR), 1)),
                    StructureTemplatePool.Projection.RIGID));
}