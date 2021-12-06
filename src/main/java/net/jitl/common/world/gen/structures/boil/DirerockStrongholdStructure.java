package net.jitl.common.world.gen.structures.boil;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.jitl.JITL;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;
import ru.timeconqueror.timecore.api.util.GenHelper;

import java.util.List;

public class DirerockStrongholdStructure extends Structure<NoFeatureConfig> {
    private static final List<MobSpawnInfo.Spawners> SPAWNERS_LIST = ImmutableList.of(
            new MobSpawnInfo.Spawners(EntityType.PIG, 20, 1, 10)
    );

    public DirerockStrongholdStructure(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return DirerockStrongholdStructure.Start::new;
    }

    @Override
    public GenerationStage.Decoration step() {
        return GenerationStage.Decoration.UNDERGROUND_STRUCTURES;
    }

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return SPAWNERS_LIST;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator_, BiomeProvider biomeProvider_, long long_, SharedSeedRandom sharedSeedRandom_, int int_, int int1_, Biome biome_, ChunkPos chunkPos_, NoFeatureConfig featureConfig_) {
        for (Biome biome : biomeProvider_.getBiomesWithin(int_ * 16 + 9, chunkGenerator_.getSeaLevel(), int1_ * 16 + 9, 32)) {
            if (!biome.getGenerationSettings().isValidStart(this)) {
                return false;
            }
        }
        return true;
    }

    public static class Start extends StructureStart<NoFeatureConfig> {

        public Start(Structure<NoFeatureConfig> structure_, int int_, int int1_, MutableBoundingBox mutableBoundingBox_, int int2_, long long_) {
            super(structure_, int_, int1_, mutableBoundingBox_, int2_, long_);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistries_, ChunkGenerator chunkGenerator_, TemplateManager templateManager_, int x, int z, Biome biome_, NoFeatureConfig featureConfig_) {
            int chunkX = (x << 4) + 7;
            int chunkZ = (z << 4) + 7;

            int surface = GenHelper.getAverageFirstFreeHeight(chunkGenerator_, x, z, x + 7, z + 7);
            surface -= 1;

            BlockPos pos = new BlockPos(chunkX, surface, chunkZ);
            if (chunkGenerator_.getBaseHeight(chunkX, chunkZ, Heightmap.Type.WORLD_SURFACE_WG) > 0) {
                JigsawManager.addPieces(dynamicRegistries_,
                        new VillageConfig(() -> dynamicRegistries_.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(JITL.rl("boil/direrock_stronghold/stairways")), 5),
                        AbstractVillagePiece::new,
                        chunkGenerator_,
                        templateManager_,
                        pos,
                        pieces,
                        random,
                        true,
                        true);
            }
            pieces.forEach(piece -> piece.move(0, 0, 0));
            calculateBoundingBox();
            moveInsideHeights(random, 64, 67);
        }
    }
}
