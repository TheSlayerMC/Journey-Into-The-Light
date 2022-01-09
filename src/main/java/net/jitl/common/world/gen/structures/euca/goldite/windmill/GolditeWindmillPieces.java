package net.jitl.common.world.gen.structures.euca.goldite.windmill;

import com.google.common.collect.ImmutableMap;
import net.jitl.JITL;
import net.jitl.init.world.JStructurePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;

import java.util.List;
import java.util.Map;

public class GolditeWindmillPieces {
/*
    public static final int BB_WIDTH = 20;
    public static final ResourceLocation WINDMILL = JITL.rl("euca/goldite_windmill");

    private static final Map<ResourceLocation, BlockPos> OFFSETS = ImmutableMap.of(
            WINDMILL, new BlockPos(0, -1, 0)
    );

    public static void generate(List<StructurePiece> pieces, StructureManager templateManager, BlockPos surfacePos) {
        pieces.add(createPiece(templateManager, WINDMILL, surfacePos, true));
    }

    public static StructurePiece createPiece(StructureManager templateManager, ResourceLocation templateLocation, BlockPos pos, boolean applyGenerationNoise) {
        return new Piece(templateManager, templateLocation, pos);
    }

    public static class Piece extends TunedTemplateStructurePiece {

        public Piece(StructureManager templateManager, ResourceLocation templateLocation, BlockPos pos) {
            this(JStructurePieces.GOLDITE_WINDMILL.get(), templateManager, templateLocation, pos);
        }

        public Piece(StructureManager templateManager, CompoundTag nbt) {
            this(JStructurePieces.GOLDITE_WINDMILL.get(), templateManager, nbt);
        }

        private Piece(StructurePieceType type, StructureManager templateManager, ResourceLocation templateLocation, BlockPos pos) {
            super(type, templateManager, templateLocation, pos);
            this.templatePosition = pos.offset(OFFSETS.get(templateLocation));
        }

        private Piece(StructurePieceType type, StructureManager templateManager, CompoundTag nbt) {
            super(type, templateManager, nbt);
        }

        @Override
        protected StructurePlaceSettings makePlacementSettings() {
            return new StructurePlaceSettings();
        }
    }*/
}
