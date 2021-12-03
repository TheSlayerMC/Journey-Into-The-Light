package net.jitl.common.world.gen.jigsaw;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.jitl.JITL;
import net.jitl.init.JProcessorLists;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;

public class JigsawStarters {
    //TODO: fix processor
    public static final JigsawPattern ESKIMO_CAMP_START = JigsawPatternRegistry.register(
            new JigsawPattern(JITL.rl("frozen/eskimo_camp/starting_well"),
                    new ResourceLocation("empty"),
                    ImmutableList.of(
                            Pair.of(JigsawPiece.single(JITL.MODID + ":frozen/eskimo_camp/eskimo_starting_well", JProcessorLists.IGLOO_PROCESSOR), 1)),
                    JigsawPattern.PlacementBehaviour.RIGID));
}