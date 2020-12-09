package net.jitl.common.world.gen.structures;

import com.google.common.collect.ImmutableMap;
import net.jitl.JITL;
import net.jitl.init.JStructurePieces;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import ru.timeconqueror.timecore.api.common.world.structure.INoNoiseStructurePiece;

import java.util.Map;
import java.util.Random;

public class GuardianTowerPieces {
    public static final int BB_WIDTH = 25;
    public static final ResourceLocation FLOOR_PIECE = JITL.rl("overworld/guardian_tower/floor");
    public static final ResourceLocation FIRST_FLOOR_DECORATION_PIECE = JITL.rl("overworld/guardian_tower/first_floor_decoration");
    private static final Map<ResourceLocation, BlockPos> OFFSETS = ImmutableMap.of(
            FIRST_FLOOR_DECORATION_PIECE, BlockPos.ZERO,
            FLOOR_PIECE, new BlockPos(1, 0, 1)
    );
//    private static final int H_CENTER_MINUS_ONE = BB_WIDTH / 2;

    public static class NoGrassTouchedPiece extends Piece implements INoNoiseStructurePiece {
        public NoGrassTouchedPiece(TemplateManager templateManager, ResourceLocation templateLocation, BlockPos pos) {
            super(JStructurePieces.GUARDIAN_TOWER_NO_GRASS_TOUCHED_PIECE, templateManager, templateLocation, pos);
        }

        public NoGrassTouchedPiece(TemplateManager templateManager, CompoundNBT nbt) {
            super(JStructurePieces.GUARDIAN_TOWER_NO_GRASS_TOUCHED_PIECE, templateManager, nbt);
        }
    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation templateLocation;

        public Piece(TemplateManager templateManager, ResourceLocation templateLocation, BlockPos pos) {
            this(JStructurePieces.GUARDIAN_TOWER_PIECE, templateManager, templateLocation, pos);
        }

        public Piece(TemplateManager templateManager, CompoundNBT nbt) {
            this(JStructurePieces.GUARDIAN_TOWER_PIECE, templateManager, nbt);
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
