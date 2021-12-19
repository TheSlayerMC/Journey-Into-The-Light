package net.jitl.common.world.gen.structures.frozen.guardianruins;

import com.google.common.collect.ImmutableMap;
import net.jitl.JITL;
import net.jitl.init.JBlocks;
import net.jitl.init.JStructurePieces;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.TemplateManager;
import ru.timeconqueror.timecore.api.common.world.structure.TunedTemplateStructurePiece;
import ru.timeconqueror.timecore.api.common.world.structure.processor.RandomizeBlockProcessor;

import java.util.List;
import java.util.Map;

public class GuardianRuinPieces {
    public static final int BB_WIDTH = 20;
    public static final ResourceLocation RUINS = JITL.rl("frozen/guardian_ruin");
    private static final Map<ResourceLocation, BlockPos> OFFSETS = ImmutableMap.of(
            RUINS, new BlockPos(0, -1, 0)
    );

    public static void generate(List<StructurePiece> pieces, TemplateManager templateManager, BlockPos surfacePos) {
        pieces.add(createPiece(templateManager, RUINS, surfacePos, true));
    }

    public static StructurePiece createPiece(TemplateManager templateManager, ResourceLocation templateLocation, BlockPos pos, boolean applyGenerationNoise) {
        return new GuardianRuinPieces.Piece(templateManager, templateLocation, pos);
    }

    public static class Piece extends TunedTemplateStructurePiece {
        public Piece(TemplateManager templateManager, ResourceLocation templateLocation, BlockPos pos) {
            this(JStructurePieces.GUARDIAN_RUIN.get(), templateManager, templateLocation, pos);
        }

        public Piece(TemplateManager templateManager, CompoundNBT nbt) {
            this(JStructurePieces.GUARDIAN_RUIN.get(), templateManager, nbt);
        }

        private Piece(IStructurePieceType type, TemplateManager templateManager, ResourceLocation templateLocation, BlockPos pos) {
            super(type, templateManager, templateLocation, pos);
            this.templatePosition = pos.offset(OFFSETS.get(templateLocation));
        }

        private Piece(IStructurePieceType type, TemplateManager templateManager, CompoundNBT nbt) {
            super(type, templateManager, nbt);
        }

        @Override
        protected PlacementSettings makePlacementSettings() {
            return new PlacementSettings()
                    .addProcessor(new RandomizeBlockProcessor(JBlocks.PACKED_ICE_BRICKS, Blocks.ICE, 50))
                    .addProcessor(new RandomizeBlockProcessor(JBlocks.PACKED_ICE_BRICKS, Blocks.PACKED_ICE, 50))
                    .addProcessor(new RandomizeBlockProcessor(Blocks.BLUE_ICE, Blocks.PACKED_ICE, 50))
                    .addProcessor(new RandomizeBlockProcessor(Blocks.BLUE_ICE, Blocks.ICE, 50));
        }
    }
}
