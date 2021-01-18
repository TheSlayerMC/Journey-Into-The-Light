package net.jitl.common.world.gen.structures.guardian;

import com.google.common.collect.ImmutableMap;
import net.jitl.JITL;
import net.jitl.init.JStructurePieces;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import ru.timeconqueror.timecore.api.common.world.structure.INoNoiseStructurePiece;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class GuardianTowerPieces {
    public static final int BB_WIDTH = 25;
    public static final ResourceLocation FLOOR_PIECE = JITL.rl("overworld/guardian_tower/floor");
    public static final ResourceLocation FIRST_FLOOR_DECORATION_PIECE = JITL.rl("overworld/guardian_tower/first_floor_decoration");
    public static final ResourceLocation TOP_FLOOR_PIECE = JITL.rl("overworld/guardian_tower/top_floor");
    public static final ResourceLocation TOP_FLOOR_DECORATION_PIECE = JITL.rl("overworld/guardian_tower/top_floor_decoration");
    private static final Map<ResourceLocation, BlockPos> OFFSETS = ImmutableMap.of(
            TOP_FLOOR_PIECE, BlockPos.ZERO,
            FIRST_FLOOR_DECORATION_PIECE, new BlockPos(2, 0, 2),
            FLOOR_PIECE, new BlockPos(3, 0, 3),
            TOP_FLOOR_DECORATION_PIECE, new BlockPos(1, 0, 1)
    );

    public static void generate(List<StructurePiece> pieces, TemplateManager templateManager, BlockPos surfacePos) {
        BlockPos changeable = surfacePos;
        for (int i = 0; i < 4; i++) {
            pieces.add(createPiece(templateManager, GuardianTowerPieces.FLOOR_PIECE, changeable, false));
            changeable = changeable.above(6);
        }

        pieces.add(createPiece(templateManager, GuardianTowerPieces.TOP_FLOOR_PIECE, changeable, false));
        pieces.add(createPiece(templateManager, GuardianTowerPieces.TOP_FLOOR_DECORATION_PIECE, changeable.below(10), false));
        pieces.add(createPiece(templateManager, GuardianTowerPieces.FIRST_FLOOR_DECORATION_PIECE, surfacePos, true));
    }

    public static StructurePiece createPiece(TemplateManager templateManager, ResourceLocation templateLocation, BlockPos pos, boolean applyGenerationNoise) {
        return applyGenerationNoise ? new GuardianTowerPieces.Piece(templateManager, templateLocation, pos) : new GuardianTowerPieces.NoGrassTouchedPiece(templateManager, templateLocation, pos);
    }

    public static class NoGrassTouchedPiece extends Piece implements INoNoiseStructurePiece {
        public NoGrassTouchedPiece(TemplateManager templateManager, ResourceLocation templateLocation, BlockPos pos) {
            super(JStructurePieces.GUARDIAN_TOWER_NO_GRASS_TOUCHED_PIECE.get(), templateManager, templateLocation, pos);
        }

        public NoGrassTouchedPiece(TemplateManager templateManager, CompoundNBT nbt) {
            super(JStructurePieces.GUARDIAN_TOWER_NO_GRASS_TOUCHED_PIECE.get(), templateManager, nbt);
        }
    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation templateLocation;

        public Piece(TemplateManager templateManager, ResourceLocation templateLocation, BlockPos pos) {
            this(JStructurePieces.GUARDIAN_TOWER_PIECE.get(), templateManager, templateLocation, pos);
        }

        public Piece(TemplateManager templateManager, CompoundNBT nbt) {
            this(JStructurePieces.GUARDIAN_TOWER_PIECE.get(), templateManager, nbt);
        }

        protected Piece(IStructurePieceType type, TemplateManager templateManager, ResourceLocation templateLocation, BlockPos pos) {
            super(type, 0/*genDepth*/);
            this.templateLocation = templateLocation;
            this.templatePosition = pos.offset(OFFSETS.get(templateLocation));
            loadTemplate(templateManager);
        }

        protected Piece(IStructurePieceType type, TemplateManager templateManager, CompoundNBT nbt) {
            super(type, nbt);
            this.templateLocation = new ResourceLocation(nbt.getString("template"));
            loadTemplate(templateManager);
        }

        @Override
        protected void addAdditionalSaveData(CompoundNBT tagCompound) {
            super.addAdditionalSaveData(tagCompound);
            tagCompound.putString("template", this.templateLocation.toString());
        }

        private void loadTemplate(TemplateManager templateManager) {
            Template template = templateManager.getOrCreate(this.templateLocation);
            PlacementSettings placementsettings = new PlacementSettings();
//                    .setRotation(this.rotation)
//                    .setMirror(Mirror.NONE)
//                    .setRotationPivot()
//                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            this.setup(template, this.templatePosition, placementsettings);
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand, MutableBoundingBox sbb) {

        }
    }
}
