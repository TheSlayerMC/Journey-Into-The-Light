package net.jitl.common.world.gen.structures.nether;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import net.jitl.core.JITL;
import net.jitl.core.init.world.JStructurePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;

import java.util.Map;
import java.util.Random;

public class NetherTowerStructure extends StructureFeature<NoneFeatureConfiguration> {

    public static final ResourceLocation TOWER = JITL.rl("boil/nether_tower");

    static final Map<ResourceLocation, BlockPos> PIVOTS = ImmutableMap.of(TOWER, new BlockPos(10, 15, 10));
    private static final Map<ResourceLocation, BlockPos> OFFSETS = ImmutableMap.of(
            TOWER, new BlockPos(0, -1, 0)
    );

    public NetherTowerStructure(Codec<NoneFeatureConfiguration> configCodec_) {
        super(configCodec_, PieceGeneratorSupplier.simple(PieceGeneratorSupplier.checkForBiomeOnTop(Heightmap.Types.WORLD_SURFACE_WG), NetherTowerStructure::generatePieces));
    }

    private static void generatePieces(StructurePiecesBuilder collector_, PieceGenerator.Context<NoneFeatureConfiguration> context_) {
        BlockPos blockPos = context_.chunkPos().getWorldPosition();
        int landHeight = context_.chunkGenerator().getFirstOccupiedHeight(blockPos.getX(), blockPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context_.heightAccessor());
        BlockPos blockpos = new BlockPos(context_.chunkPos().getMinBlockX(), landHeight - 1, context_.chunkPos().getMinBlockZ());
        Rotation rotation = Rotation.getRandom(context_.random());
        NetherTowerStructure.addPieces(context_.structureManager(), blockpos, rotation, collector_, context_.random());
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.UNDERGROUND_STRUCTURES;
    }

    public static void addPieces(StructureManager structureManager, BlockPos pos, Rotation rotation, StructurePieceAccessor pieces, Random random) {
        pieces.addPiece(createPiece(structureManager, TOWER, pos, rotation, true));
    }

    public static StructurePiece createPiece(StructureManager templateManager, ResourceLocation templateLocation, BlockPos pos, Rotation rotation, boolean applyGenerationNoise) {
        return new NetherTowerStructure.Piece(templateManager, templateLocation, pos, rotation, 0);
    }

    public static class Piece extends TemplateStructurePiece {
        public Piece(StructureManager structureManager_, ResourceLocation location_, BlockPos pos_, Rotation rotation, int down_) {
            super(JStructurePieces.NETHER_TOWER.get(), 0, structureManager_, location_, location_.toString(), makeSettings(rotation, location_), makePosition(location_, pos_, down_));
        }

        public Piece(StructurePieceSerializationContext structurePieceSerializationContext, CompoundTag compoundTag) {
            super(JStructurePieces.NETHER_TOWER.get(), compoundTag, structurePieceSerializationContext.structureManager(), (resourceLocation_) -> makeSettings(Rotation.valueOf(compoundTag.getString("Rot")), resourceLocation_));
        }

        private static StructurePlaceSettings makeSettings(Rotation rotation_, ResourceLocation location_) {
            return (new StructurePlaceSettings())
                    .setRotation(rotation_)
                    .setMirror(Mirror.NONE)
                    .setRotationPivot(NetherTowerStructure.PIVOTS.get(location_));
        }

        private static BlockPos makePosition(ResourceLocation location_, BlockPos pos_, int down_) {
            return pos_.offset(NetherTowerStructure.OFFSETS.get(location_)).below(down_);
        }

        @Override
        protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag tag) {
            super.addAdditionalSaveData(context, tag);
            tag.putString("Rot", this.placeSettings.getRotation().name());
        }

        @Override
        protected void handleDataMarker(String marker_, BlockPos pos_, ServerLevelAccessor level_, Random random_, BoundingBox box_) {

        }
    }
}
