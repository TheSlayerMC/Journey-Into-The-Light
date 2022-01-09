package net.jitl.common.world.gen.structures.overworld.guardian;

import com.mojang.serialization.Codec;
import net.jitl.JITL;
import net.jitl.init.world.JStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import ru.timeconqueror.timecore.api.util.GenHelper;

public class GuardianTowerStructure extends StructureFeature<NoneFeatureConfiguration> {
    public GuardianTowerStructure(Codec<NoneFeatureConfiguration> configCodec_, PieceGeneratorSupplier<NoneFeatureConfiguration> piecesGenerator_) {
        super(configCodec_, piecesGenerator_);
    }
    /*public GuardianTowerStructure(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return Start::new;
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    public static class Start extends StructureStart<NoneFeatureConfiguration> {
        public Start(StructureFeature<NoneFeatureConfiguration> structure, int chunkX, int chunkZ, BoundingBox mutableBoundingBox_, int references, long seed) {
            super(structure, chunkX, chunkZ, mutableBoundingBox_, references, seed);
        }

        public void generatePieces(RegistryAccess dynamicRegistries, ChunkGenerator chunkGenerator, StructureManager templateManager, int chunkX, int chunkZ, Biome biome_, NoneFeatureConfiguration featureConfig_) {
            int x = chunkX << 4;
            int z = chunkZ << 4;

            int surface = GenHelper.getAverageFirstFreeHeight(chunkGenerator, x, z, x + GuardianTowerPieces.BB_WIDTH, z + GuardianTowerPieces.BB_WIDTH);
            surface -= 1;

            BlockPos start = new BlockPos(x, surface, z);
            JITL.LOGGER.debug(JStructures.STRUCTURE_MARKER, "Attempting to generate {} on {}", GuardianTowerStructure.class.getSimpleName(), start);

            GuardianTowerPieces.generate(pieces, templateManager, start);

            this.calculateBoundingBox();
        }
    }*/
}
