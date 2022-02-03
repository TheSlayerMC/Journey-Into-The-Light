package net.jitl.common.world.gen.structures.overworld;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.jitl.core.JITL;
import net.jitl.core.init.JEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.List;


public class IllagerBunkerStructure extends StructureFeature<JigsawConfiguration> {
    private static final List<MobSpawnSettings.SpawnerData> SPAWNERS_LIST = ImmutableList.of(
            new MobSpawnSettings.SpawnerData(EntityType.PILLAGER, 20, 1, 10),
            new MobSpawnSettings.SpawnerData(EntityType.VINDICATOR, 50, 1, 10),
            new MobSpawnSettings.SpawnerData(EntityType.ILLUSIONER, 2, 1, 1),
            new MobSpawnSettings.SpawnerData(EntityType.EVOKER, 5, 1, 1),
            new MobSpawnSettings.SpawnerData(JEntities.ILLAGER_MECH_TYPE, 5, 1, 1)
    );

    public IllagerBunkerStructure(Codec<JigsawConfiguration> codec) {
        super(codec, (context) -> {
            JigsawConfiguration config = new JigsawConfiguration(() -> context.registryAccess().ownedRegistryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(JITL.rl("overworld/illager_bunker/hallways")), 300);
            PieceGeneratorSupplier.Context<JigsawConfiguration> structureContext = new PieceGeneratorSupplier.Context<>(context.chunkGenerator(), context.biomeSource(), context.seed(), context.chunkPos(), config, context.heightAccessor(), context.validBiome(), context.structureManager(), context.registryAccess());
            BlockPos startPos = new BlockPos(context.chunkPos().getMinBlockX(), 24, context.chunkPos().getMinBlockZ());
            return JigsawPlacement.addPieces(structureContext, PoolElementStructurePiece::new, startPos, false, false);
        });
    }

    @Override
    public List<MobSpawnSettings.SpawnerData> getDefaultSpawnList(MobCategory category) {
        return SPAWNERS_LIST;
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.UNDERGROUND_STRUCTURES;
    }

    @Override
    public String getFeatureName() {
        return "jitl:illager_bunker";
    }
}