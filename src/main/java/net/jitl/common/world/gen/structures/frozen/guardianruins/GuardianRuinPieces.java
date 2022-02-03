package net.jitl.common.world.gen.structures.frozen.guardianruins;

import com.google.common.collect.ImmutableMap;
import net.jitl.core.JITL;
import net.jitl.core.init.JBlocks;
import net.jitl.core.init.world.JStructurePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import ru.timeconqueror.timecore.api.common.world.structure.processor.RandomizeBlockProcessor;

import java.util.Map;
import java.util.Random;

public class GuardianRuinPieces {
    public static final int BB_WIDTH = 20;
    public static final ResourceLocation RUINS = JITL.rl("frozen/guardian_ruin");
    static final Map<ResourceLocation, BlockPos> PIVOTS = ImmutableMap.of(RUINS, new BlockPos(3, 5, 5));
    private static final Map<ResourceLocation, BlockPos> OFFSETS = ImmutableMap.of(
            RUINS, new BlockPos(0, -1, 0)
    );

    public static void addPieces(StructureManager structureManager, BlockPos pos, Rotation rotation, StructurePieceAccessor pieces, Random random) {
        pieces.addPiece(createPiece(structureManager, RUINS, pos, rotation, true));
    }

    public static StructurePiece createPiece(StructureManager templateManager, ResourceLocation templateLocation, BlockPos pos, Rotation rotation, boolean applyGenerationNoise) {
        return new Piece(templateManager, templateLocation, pos, rotation, 0);
    }

    public static class Piece extends TemplateStructurePiece {
        public Piece(StructureManager structureManager_, ResourceLocation location_, BlockPos pos_, Rotation rotation, int down_) {
            super(JStructurePieces.GUARDIAN_RUIN.get(), 0, structureManager_, location_, location_.toString(), makeSettings(rotation, location_), makePosition(location_, pos_, down_));
        }

        public Piece(StructurePieceSerializationContext structurePieceSerializationContext, CompoundTag compoundTag) {
            super(JStructurePieces.GUARDIAN_RUIN.get(), compoundTag, structurePieceSerializationContext.structureManager(), (resourceLocation_) -> makeSettings(Rotation.valueOf(compoundTag.getString("Rot")), resourceLocation_));
        }

        private static StructurePlaceSettings makeSettings(Rotation rotation_, ResourceLocation location_) {
            return (new StructurePlaceSettings()).addProcessor(new RandomizeBlockProcessor(JBlocks.PACKED_ICE_BRICKS, Blocks.ICE, 50))
                    .addProcessor(new RandomizeBlockProcessor(JBlocks.PACKED_ICE_BRICKS, Blocks.PACKED_ICE, 50))
                    .addProcessor(new RandomizeBlockProcessor(Blocks.BLUE_ICE, Blocks.PACKED_ICE, 50))
                    .addProcessor(new RandomizeBlockProcessor(Blocks.BLUE_ICE, Blocks.ICE, 50))
                    .setRotation(rotation_)
                    .setMirror(Mirror.NONE)
                    .setRotationPivot(GuardianRuinPieces.PIVOTS.get(location_))
                    .addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
        }

        private static BlockPos makePosition(ResourceLocation location_, BlockPos pos_, int down_) {
            return pos_.offset(GuardianRuinPieces.OFFSETS.get(location_)).below(down_);
        }

        @Override
        protected void addAdditionalSaveData(StructurePieceSerializationContext context_, CompoundTag tag_) {
            super.addAdditionalSaveData(context_, tag_);
            tag_.putString("Rot", this.placeSettings.getRotation().name());
        }

        @Override
        protected void handleDataMarker(String marker_, BlockPos pos_, ServerLevelAccessor level_, Random random_, BoundingBox box_) {

        }
    }
}
