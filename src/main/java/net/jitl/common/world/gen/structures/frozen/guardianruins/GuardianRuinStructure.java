package net.jitl.common.world.gen.structures.frozen.guardianruins;

import com.mojang.serialization.Codec;
import net.jitl.JITL;
import net.jitl.init.world.JStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature.StructureStartFactory;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.util.GenHelper;

public class GuardianRuinStructure extends StructureFeature<NoneFeatureConfiguration> {
    public GuardianRuinStructure(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public @NotNull StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return Start::new;
    }

    @Override
    public @NotNull Decoration step() {
        return Decoration.SURFACE_STRUCTURES;
    }

    public static class Start extends StructureStart<NoneFeatureConfiguration> {
        public Start(StructureFeature<NoneFeatureConfiguration> structure, int chunkX, int chunkZ, BoundingBox mutableBoundingBox_, int references, long seed) {
            super(structure, chunkX, chunkZ, mutableBoundingBox_, references, seed);
        }

        public void generatePieces(@NotNull RegistryAccess dynamicRegistries, @NotNull ChunkGenerator chunkGenerator, @NotNull StructureManager templateManager, int chunkX, int chunkZ, @NotNull Biome biome_, @NotNull NoneFeatureConfiguration featureConfig_) {
            int x = chunkX << 4;
            int z = chunkZ << 4;

            int surface = GenHelper.getAverageFirstFreeHeight(chunkGenerator, x, z, x + GuardianRuinPieces.BB_WIDTH, z + GuardianRuinPieces.BB_WIDTH);
            surface -= 1;

            BlockPos start = new BlockPos(x, surface, z);
            JITL.LOGGER.debug(JStructures.STRUCTURE_MARKER, "Attempting to generate {} on {}", GuardianRuinPieces.class.getSimpleName(), start);

            GuardianRuinPieces.generate(pieces, templateManager, start);

            this.calculateBoundingBox();
        }
    }
}
