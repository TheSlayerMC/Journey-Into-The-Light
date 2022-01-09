package net.jitl.common.world.gen.structures.overworld;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.jitl.JITL;
import net.jitl.init.JEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.List;


public class IllagerBunkerStructure extends StructureFeature<NoneFeatureConfiguration> {
    public IllagerBunkerStructure(Codec<NoneFeatureConfiguration> configCodec_, PieceGeneratorSupplier<NoneFeatureConfiguration> piecesGenerator_, PostPlacementProcessor postPlacementProcessor_) {
        super(configCodec_, piecesGenerator_, postPlacementProcessor_);
    }
   /* private static final List<MobSpawnSettings.SpawnerData> SPAWNERS_LIST = ImmutableList.of(
            new MobSpawnSettings.SpawnerData(EntityType.PILLAGER, 20, 1, 10),
            new MobSpawnSettings.SpawnerData(EntityType.VINDICATOR, 50, 1, 10),
            new MobSpawnSettings.SpawnerData(EntityType.ILLUSIONER, 2, 1, 1),
            new MobSpawnSettings.SpawnerData(EntityType.EVOKER, 5, 1, 1),
            new MobSpawnSettings.SpawnerData(JEntities.ILLAGER_MECH_TYPE, 5, 1, 1)
    );

    public IllagerBunkerStructure(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return Start::new;
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.UNDERGROUND_STRUCTURES;
    }

    @Override
    public List<MobSpawnSettings.SpawnerData> getDefaultSpawnList() {
        return SPAWNERS_LIST;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator_, BiomeSource biomeProvider_, long long_, WorldgenRandom sharedSeedRandom_, int int_, int int1_, Biome biome_, ChunkPos chunkPos_, NoneFeatureConfiguration featureConfig_) {
        for (Biome biome : biomeProvider_.getBiomesWithin(int_ * 16 + 9, chunkGenerator_.getSeaLevel(), int1_ * 16 + 9, 32)) {
            if (!biome.getGenerationSettings().isValidStart(this)) {
                return false;
            }
        }
        return true;
    }

    public static class Start extends StructureStart<NoneFeatureConfiguration> {

        public Start(StructureFeature<NoneFeatureConfiguration> structure_, int int_, int int1_, BoundingBox mutableBoundingBox_, int int2_, long long_) {
            super(structure_, int_, int1_, mutableBoundingBox_, int2_, long_);
        }

        @Override
        public void generatePieces(RegistryAccess dynamicRegistries_, ChunkGenerator chunkGenerator_, StructureManager templateManager_, int x, int z, Biome biome_, NoneFeatureConfiguration featureConfig_) {
            int chunkX = (x << 4) + 7;
            int chunkZ = (z << 4) + 7;
            BlockPos pos = new BlockPos(chunkX, 0, chunkZ);
            if (chunkGenerator_.getBaseHeight(chunkX, chunkZ, Heightmap.Types.WORLD_SURFACE_WG) > 0) {
                JigsawPlacement.addPieces(dynamicRegistries_,
                        new JigsawConfiguration(() -> dynamicRegistries_.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(JITL.rl("overworld/illager_bunker/hallways")), 300),
                        PoolElementStructurePiece::new,
                        chunkGenerator_,
                        templateManager_,
                        pos,
                        pieces,
                        random,
                        true,
                        true);
            }
            pieces.forEach(piece -> piece.move(0, 5, 0));
            calculateBoundingBox();
            moveInsideHeights(random, 16, 32);
        }
    }*/
}
