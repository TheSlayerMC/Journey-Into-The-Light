package net.jitl.common.world.gen.structures.overworld;

import com.mojang.serialization.Codec;
import net.jitl.JITL;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;


public class IllagerBunkerStructure extends StructureFeature<JigsawConfiguration> {

    public IllagerBunkerStructure(Codec<JigsawConfiguration> codec) {
        super(codec, (context) -> {
            JigsawConfiguration config = new JigsawConfiguration(() -> context.registryAccess().ownedRegistryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(JITL.rl("overworld/illager_bunker/hallways")), 300);
            PieceGeneratorSupplier.Context<JigsawConfiguration> structureContext = new PieceGeneratorSupplier.Context<>(context.chunkGenerator(), context.biomeSource(), context.seed(), context.chunkPos(), config, context.heightAccessor(), context.validBiome(), context.structureManager(), context.registryAccess());
            BlockPos startPos = new BlockPos(context.chunkPos().getMinBlockX(), 24, context.chunkPos().getMinBlockZ());
            return JigsawPlacement.addPieces(structureContext, PoolElementStructurePiece::new, startPos, false, false);
        });
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