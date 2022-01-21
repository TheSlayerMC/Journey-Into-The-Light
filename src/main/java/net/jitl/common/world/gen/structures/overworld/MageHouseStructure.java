package net.jitl.common.world.gen.structures.overworld;

import net.jitl.core.JITL;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class MageHouseStructure extends StructureFeature<JigsawConfiguration> {

    public static final ResourceLocation POOL = JITL.rl("overworld/mage_house");

    public MageHouseStructure() {
        super(JigsawConfiguration.CODEC, context_ -> {
            if (!isFeatureChunk(context_)) {
                return Optional.empty();
            } else {
                return createPieceGenerator(context_);
            }
        }, PostPlacementProcessor.NONE);
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    public static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        BlockPos pos = context.chunkPos().getWorldPosition();
        int surfaceHeight = context.chunkGenerator().getFirstOccupiedHeight(pos.getX(), pos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
        NoiseColumn noise = context.chunkGenerator().getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor());
        BlockState topBlock = noise.getBlock(surfaceHeight);
        return topBlock.getFluidState().isEmpty();
    }

    public static @NotNull Optional<PieceGenerator<JigsawConfiguration>> createPieceGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context){
        BlockPos pos = context.chunkPos().getMiddleBlockPosition(0);
        var newConfig = new JigsawConfiguration(
                () -> context.registryAccess().ownedRegistryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(POOL), 5
        );

        var newContext = createContextWithConfig(context, newConfig);

        var generator = JigsawPlacement.addPieces(newContext, PoolElementStructurePiece::new, pos, false, true);

        if(generator.isPresent()) {
            JITL.LOGGER.log(Level.INFO, MageHouseStructure.class.getName() + " Generated at " + pos);
        }

        return generator;
    }

    public static PieceGeneratorSupplier.Context<JigsawConfiguration> createContextWithConfig(PieceGeneratorSupplier.Context<JigsawConfiguration> context, JigsawConfiguration newConfig) {
        return new PieceGeneratorSupplier.Context<>(
                context.chunkGenerator(),
                context.biomeSource(),
                context.seed(),
                context.chunkPos(),
                newConfig,
                context.heightAccessor(),
                context.validBiome(),
                context.structureManager(),
                context.registryAccess()
        );
    }
}
