package net.jitl.common.world.gen.structures.overworld.guardian;

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
import ru.timeconqueror.timecore.api.common.world.structure.INoNoiseStructurePiece;
import ru.timeconqueror.timecore.api.common.world.structure.processor.RandomizeBlockProcessor;

import java.util.Map;
import java.util.Random;

public class GuardianTowerPieces {

    public static final int BB_WIDTH = 25;
    public static final ResourceLocation FLOOR_PIECE = JITL.rl("overworld/guardian_tower/floor");
    public static final ResourceLocation FIRST_FLOOR_DECORATION_PIECE = JITL.rl("overworld/guardian_tower/first_floor_decoration");
    public static final ResourceLocation TOP_FLOOR_PIECE = JITL.rl("overworld/guardian_tower/top_floor");
    public static final ResourceLocation TOP_FLOOR_DECORATION_PIECE = JITL.rl("overworld/guardian_tower/top_floor_decoration");

    static final Map<ResourceLocation, BlockPos> PIVOTS = ImmutableMap.of(FLOOR_PIECE, new BlockPos(15, 15, 15));

    private static final Map<ResourceLocation, BlockPos> OFFSETS = ImmutableMap.of(
            TOP_FLOOR_PIECE, BlockPos.ZERO,
            FIRST_FLOOR_DECORATION_PIECE, new BlockPos(2, 0, 2),
            FLOOR_PIECE, new BlockPos(3, 0, 3),
            TOP_FLOOR_DECORATION_PIECE, new BlockPos(1, 0, 1)
    );

    public static void generate(StructurePieceAccessor pieces, StructureManager templateManager, BlockPos surfacePos, Rotation rotation) {
        BlockPos changeable = surfacePos;
        for (int i = 0; i < 4; i++) {
            pieces.addPiece(createPiece(templateManager, GuardianTowerPieces.FLOOR_PIECE, changeable, rotation, false));
            changeable = changeable.above(6);
        }

        pieces.addPiece(createPiece(templateManager, GuardianTowerPieces.TOP_FLOOR_PIECE, changeable, rotation, false));
        pieces.addPiece(createPiece(templateManager, GuardianTowerPieces.TOP_FLOOR_DECORATION_PIECE, changeable.below(10), rotation, false));
        pieces.addPiece(createPiece(templateManager, GuardianTowerPieces.FIRST_FLOOR_DECORATION_PIECE, surfacePos, rotation, true));
    }

    public static StructurePiece createPiece(StructureManager templateManager, ResourceLocation templateLocation, BlockPos pos, Rotation rotation, boolean applyGenerationNoise) {
        return applyGenerationNoise ? new Piece(templateManager, templateLocation, pos, rotation, 0) : new NoGrassTouchedPiece(templateManager, templateLocation, pos, rotation, 0);
    }

    public static class NoGrassTouchedPiece extends TemplateStructurePiece implements INoNoiseStructurePiece {

        public NoGrassTouchedPiece(StructureManager structureManager_, ResourceLocation location_, BlockPos pos_, Rotation rotation, int down_) {
            super(JStructurePieces.GUARDIAN_TOWER_NO_GRASS_TOUCHED_PIECE.get(), 0, structureManager_, location_, location_.toString(), makeSettings(rotation, location_), makePosition(location_, pos_, down_));
        }

        public NoGrassTouchedPiece(StructurePieceSerializationContext structurePieceSerializationContext, CompoundTag compoundTag) {
            super(JStructurePieces.GUARDIAN_TOWER_NO_GRASS_TOUCHED_PIECE.get(), compoundTag, structurePieceSerializationContext.structureManager(), (resourceLocation_) -> makeSettings(Rotation.valueOf(compoundTag.getString("Rot")), resourceLocation_));
        }


        private static StructurePlaceSettings makeSettings(Rotation rotation_, ResourceLocation location_) {
            return (new StructurePlaceSettings()).addProcessor(new RandomizeBlockProcessor(JBlocks.PACKED_ICE_BRICKS, Blocks.ICE, 50))
                    .addProcessor(new RandomizeBlockProcessor(JBlocks.SHIELDED_DUNGEON_BRICKS, JBlocks.SHIELDED_CARVED_DUNGEON_BRICKS, 50))
                    .addProcessor(new RandomizeBlockProcessor(JBlocks.SHIELDED_DUNGEON_BRICKS, JBlocks.SHIELDED_CHISELED_DUNGEON_BRICKS, 50))
                    .addProcessor(new RandomizeBlockProcessor(JBlocks.SHIELDED_DUNGEON_BRICKS, JBlocks.SHIELDED_CRACKED_DUNGEON_BRICKS, 50))
                    .setRotation(rotation_)
                    .setMirror(Mirror.NONE)
                    .setRotationPivot(GuardianTowerPieces.PIVOTS.get(location_))
                    .addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
        }

        private static BlockPos makePosition(ResourceLocation location_, BlockPos pos_, int down_) {
            return pos_.offset(GuardianTowerPieces.OFFSETS.get(location_)).below(down_);
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

    public static class Piece extends TemplateStructurePiece {
        public Piece(StructureManager structureManager_, ResourceLocation location_, BlockPos pos_, Rotation rotation, int down_) {
            super(JStructurePieces.GUARDIAN_TOWER_PIECE.get(), 0, structureManager_, location_, location_.toString(), makeSettings(rotation, location_), makePosition(location_, pos_, down_));
        }

        public Piece(StructurePieceSerializationContext structurePieceSerializationContext, CompoundTag compoundTag) {
            super(JStructurePieces.GUARDIAN_TOWER_PIECE.get(), compoundTag, structurePieceSerializationContext.structureManager(), (resourceLocation_) -> makeSettings(Rotation.valueOf(compoundTag.getString("Rot")), resourceLocation_));
        }

        private static StructurePlaceSettings makeSettings(Rotation rotation_, ResourceLocation location_) {
            return (new StructurePlaceSettings()).addProcessor(new RandomizeBlockProcessor(JBlocks.PACKED_ICE_BRICKS, Blocks.ICE, 50))
                    .addProcessor(new RandomizeBlockProcessor(JBlocks.SHIELDED_DUNGEON_BRICKS, JBlocks.SHIELDED_CARVED_DUNGEON_BRICKS, 50))
                    .addProcessor(new RandomizeBlockProcessor(JBlocks.SHIELDED_DUNGEON_BRICKS, JBlocks.SHIELDED_CHISELED_DUNGEON_BRICKS, 50))
                    .addProcessor(new RandomizeBlockProcessor(JBlocks.SHIELDED_DUNGEON_BRICKS, JBlocks.SHIELDED_CRACKED_DUNGEON_BRICKS, 50))
                    .setRotation(rotation_)
                    .setMirror(Mirror.NONE)
                    .setRotationPivot(GuardianTowerPieces.PIVOTS.get(location_))
                    .addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
        }

        private static BlockPos makePosition(ResourceLocation location_, BlockPos pos_, int down_) {
            return pos_.offset(GuardianTowerPieces.OFFSETS.get(location_)).below(down_);
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
