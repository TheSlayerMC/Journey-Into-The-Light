package net.jitl.common.world.gen.structures.euca.goldite.windmill;

import com.mojang.serialization.Codec;
import net.jitl.JITL;
import net.jitl.init.JStructures;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.util.GenHelper;

public class GolditeWindmillStructure extends Structure<NoFeatureConfig> {

    public GolditeWindmillStructure(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public @NotNull IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    @Override
    public GenerationStage.@NotNull Decoration step() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    public static class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox_, int references, long seed) {
            super(structure, chunkX, chunkZ, mutableBoundingBox_, references, seed);
        }

        public void generatePieces(@NotNull DynamicRegistries dynamicRegistries, @NotNull ChunkGenerator chunkGenerator, @NotNull TemplateManager templateManager, int chunkX, int chunkZ, @NotNull Biome biome_, @NotNull NoFeatureConfig featureConfig_) {
            int x = chunkX << 4;
            int z = chunkZ << 4;

            int surface = GenHelper.getAverageFirstFreeHeight(chunkGenerator, x, z, x + GolditeWindmillPieces.BB_WIDTH, z + GolditeWindmillPieces.BB_WIDTH);
            surface -= 1;

            BlockPos start = new BlockPos(x, surface, z);
            JITL.LOGGER.debug(JStructures.STRUCTURE_MARKER, "Attempting to generate {} on {}", GolditeWindmillPieces.class.getSimpleName(), start);

            GolditeWindmillPieces.generate(pieces, templateManager, start);

            this.calculateBoundingBox();
        }
    }
}
