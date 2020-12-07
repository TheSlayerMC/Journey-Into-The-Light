package net.jitl.common.world.gen.structures;

import net.jitl.JITL;
import net.jitl.init.JStructurePieces;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public class GuardianTowerPieces {
    //    public static final int WIDTH = 25;
    public static final int BB_WIDTH = 25;
//    private static final int H_CENTER_MINUS_ONE = BB_WIDTH / 2;

    public static class Floor extends TemplateStructurePiece {
        //        private static final int HEIGHT = 6;
        private static final ResourceLocation PATH = JITL.rl("overworld/guardian_tower/floor");

        public Floor(TemplateManager templateManager, BlockPos pos) {
            super(JStructurePieces.GUARDIAN_TOWER_PIECE, 0/*genDepth*/);
            this.templatePosition = pos;
            loadTemplate(templateManager);
        }

        public Floor(TemplateManager templateManager, CompoundNBT nbt) {
            super(JStructurePieces.GUARDIAN_TOWER_PIECE, nbt);
            loadTemplate(templateManager);
        }

        private void loadTemplate(TemplateManager templateManager) {
            Template template = templateManager.getOrCreate(PATH);
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
