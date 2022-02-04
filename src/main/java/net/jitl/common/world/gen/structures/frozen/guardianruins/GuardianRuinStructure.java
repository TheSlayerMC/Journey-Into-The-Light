package net.jitl.common.world.gen.structures.frozen.guardianruins;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;

public class GuardianRuinStructure extends StructureFeature<NoneFeatureConfiguration> {
    public GuardianRuinStructure(Codec<NoneFeatureConfiguration> configCodec_) {
        super(configCodec_, PieceGeneratorSupplier.simple(PieceGeneratorSupplier.checkForBiomeOnTop(Heightmap.Types.WORLD_SURFACE_WG), GuardianRuinStructure::generatePieces));
    }

    private static void generatePieces(StructurePiecesBuilder collector_, PieceGenerator.Context<NoneFeatureConfiguration> context_) {
        BlockPos blockPos = context_.chunkPos().getWorldPosition();
        int landHeight = context_.chunkGenerator().getFirstOccupiedHeight(blockPos.getX(), blockPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context_.heightAccessor());

        BlockPos blockpos = new BlockPos(context_.chunkPos().getMinBlockX(), landHeight - 1, context_.chunkPos().getMinBlockZ());
        Rotation rotation = Rotation.getRandom(context_.random());
        GuardianRuinPieces.addPieces(context_.structureManager(), blockpos, rotation, collector_, context_.random());
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }
}
