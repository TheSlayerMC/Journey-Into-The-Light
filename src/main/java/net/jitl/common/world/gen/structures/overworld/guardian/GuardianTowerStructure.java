package net.jitl.common.world.gen.structures.overworld.guardian;

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

public class GuardianTowerStructure extends StructureFeature<NoneFeatureConfiguration> {

    public GuardianTowerStructure(Codec<NoneFeatureConfiguration> configCodec_) {
        super(configCodec_, PieceGeneratorSupplier.simple(PieceGeneratorSupplier.checkForBiomeOnTop(Heightmap.Types.WORLD_SURFACE_WG), GuardianTowerStructure::generatePieces));
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    private static void generatePieces(StructurePiecesBuilder collector_, PieceGenerator.Context<NoneFeatureConfiguration> context_) {
        BlockPos blockPos = context_.chunkPos().getWorldPosition();
        int landHeight = context_.chunkGenerator().getFirstOccupiedHeight(blockPos.getX(), blockPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context_.heightAccessor());

        BlockPos blockpos = new BlockPos(context_.chunkPos().getMinBlockX(), landHeight - 1, context_.chunkPos().getMinBlockZ());
        Rotation rotation = Rotation.getRandom(context_.random());
        GuardianTowerPieces.generate(collector_, context_.structureManager(), blockpos, rotation);
    }
}
