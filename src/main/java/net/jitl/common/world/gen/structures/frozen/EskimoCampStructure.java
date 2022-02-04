package net.jitl.common.world.gen.structures.frozen;

import com.mojang.serialization.Codec;
import net.jitl.core.JITL;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;


public class EskimoCampStructure extends StructureFeature<JigsawConfiguration> {
    public EskimoCampStructure(Codec<JigsawConfiguration> codec) {
        super(codec, (context) -> {
            JigsawConfiguration config = new JigsawConfiguration(() -> context.registryAccess().ownedRegistryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(JITL.rl("frozen/eskimo_camp/starting_well")), 2);

            BlockPos blockPos = context.chunkPos().getWorldPosition();
            int landHeight = context.chunkGenerator().getFirstOccupiedHeight(blockPos.getX(), blockPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());

            BlockPos startPos = new BlockPos(context.chunkPos().getMinBlockX(), landHeight + 1, context.chunkPos().getMinBlockZ());

            PieceGeneratorSupplier.Context<JigsawConfiguration> structureContext = new PieceGeneratorSupplier.Context<>(context.chunkGenerator(), context.biomeSource(), context.seed(), context.chunkPos(), config, context.heightAccessor(), context.validBiome(), context.structureManager(), context.registryAccess());
            return JigsawPlacement.addPieces(structureContext, PoolElementStructurePiece::new, startPos, false, false);
        });
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    public String getFeatureName() {
        return "jitl:eskimo_camp";
    }
}
