package net.jitl.common.world.gen.structures.overworld;

import com.mojang.serialization.Codec;
import net.jitl.JITL;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
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

public class FrozenFortressStructure extends Structure<NoFeatureConfig> {
    public FrozenFortressStructure(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return FrozenFortressStructure.Start::new;
    }

    public GenerationStage.Decoration step() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    public static class Start extends StructureStart<NoFeatureConfig> {

        public Start(Structure<NoFeatureConfig> structure_, int int_, int int1_, MutableBoundingBox mutableBoundingBox_, int int2_, long long_) {
            super(structure_, int_, int1_, mutableBoundingBox_, int2_, long_);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistries_, ChunkGenerator chunkGenerator_, TemplateManager templateManager_, int x, int z, Biome biome_, NoFeatureConfig featureConfig_) {
            int chunkX = (x << 4) + 7;
            int chunkZ = (z << 4) + 7;
            BlockPos pos = new BlockPos(chunkX, 0, chunkZ);
            if (chunkGenerator_.getBaseHeight(chunkX, chunkZ, Heightmap.Type.WORLD_SURFACE_WG) > 0) {
                JigsawManager.addPieces(dynamicRegistries_,
                        new VillageConfig(() -> dynamicRegistries_.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(JITL.rl("overworld/rockite/hallways")), 300),
                        AbstractVillagePiece::new,
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
            moveInsideHeights(random, 10, 36);
        }
    }
}
