package net.jitl.common.world.gen.structures.frozen;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import net.jitl.JITL;
import net.jitl.init.JBlocks;
import net.jitl.init.JStructurePieces;
import net.jitl.init.JStructures;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import ru.timeconqueror.timecore.api.common.world.structure.TunedTemplateStructurePiece;
import ru.timeconqueror.timecore.api.common.world.structure.processor.RandomizeBlockProcessor;
import ru.timeconqueror.timecore.api.util.GenHelper;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class GuardianRuinStructure extends Structure<NoFeatureConfig> {

    public static final ResourceLocation GUARDIAN = JITL.rl("frozen/guardian_ruin");

    private static final Map<ResourceLocation, BlockPos> OFFSETS = ImmutableMap.of(
            GUARDIAN, BlockPos.ZERO
    );

    public GuardianRuinStructure(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return GuardianRuinStructure.Start::new;
    }

    @Override
    public GenerationStage.Decoration step() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    public static StructurePiece createPiece(TemplateManager templateManager, ResourceLocation templateLocation, BlockPos pos, boolean applyGenerationNoise) {
        return new GuardianRuinStructure.Piece(templateManager, templateLocation, pos);
    }

    public static void generate(List<StructurePiece> pieces, TemplateManager templateManager, BlockPos surfacePos) {
        pieces.add(createPiece(templateManager, GuardianRuinStructure.GUARDIAN, surfacePos, true));
    }

    public static class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox_, int references, long seed) {
            super(structure, chunkX, chunkZ, mutableBoundingBox_, references, seed);
        }

        public void generatePieces(DynamicRegistries dynamicRegistries, ChunkGenerator chunkGenerator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome_, NoFeatureConfig featureConfig_) {
            int x = chunkX << 4;
            int z = chunkZ << 4;

            int surface = GenHelper.getAverageFirstFreeHeight(chunkGenerator, x, z, x + 10, z + 10);
            surface -= 2;

            BlockPos start = new BlockPos(x, surface, z);
            JITL.LOGGER.debug(JStructures.STRUCTURE_MARKER, "Attempting to generate {} on {}", GuardianRuinStructure.class.getSimpleName(), start);

            generate(pieces, templateManager, start);

            this.calculateBoundingBox();
        }
    }

    public static class Piece extends TunedTemplateStructurePiece {
        private final ResourceLocation templateLocation;

        public Piece(TemplateManager templateManager, ResourceLocation templateLocation, BlockPos pos) {
            this(JStructurePieces.GUARDIAN_RUIN.get(), templateManager, templateLocation, pos);
        }

        public Piece(TemplateManager templateManager, CompoundNBT nbt) {
            this(JStructurePieces.GUARDIAN_RUIN.get(), templateManager, nbt);
        }

        protected Piece(IStructurePieceType type, TemplateManager templateManager, ResourceLocation templateLocation, BlockPos pos) {
            super(type, templateManager, templateLocation, pos);
            this.templateLocation = templateLocation;
            this.templatePosition = pos.offset(OFFSETS.get(templateLocation));
            loadTemplate(templateManager);
        }

        protected Piece(IStructurePieceType type, TemplateManager templateManager, CompoundNBT nbt) {
            super(type, templateManager, nbt);
            this.templateLocation = new ResourceLocation(nbt.getString("template"));
            loadTemplate(templateManager);
        }

        @Override
        protected void addAdditionalSaveData(CompoundNBT tagCompound) {
            super.addAdditionalSaveData(tagCompound);
            tagCompound.putString("template", this.templateLocation.toString());
        }

        private void loadTemplate(TemplateManager templateManager) {
            Random random = new Random();
            Template template = templateManager.getOrCreate(this.templateLocation);
            PlacementSettings placementsettings = new PlacementSettings()
                    .setRotation(Rotation.getRandom(random))
                    .setMirror(Mirror.NONE);
//                    .setRotationPivot()
//                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            this.setup(template, this.templatePosition, placementsettings);
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand, MutableBoundingBox sbb) {

        }

        @Override
        protected PlacementSettings makePlacementSettings() {
            return new PlacementSettings()
                    .addProcessor(new RandomizeBlockProcessor(JBlocks.PACKED_SNOW_BRICKS, JBlocks.PACKED_ICE_BRICKS))
                    .addProcessor(new RandomizeBlockProcessor(JBlocks.PACKED_SNOW_BRICKS, Blocks.ICE))
                    .addProcessor(new RandomizeBlockProcessor(JBlocks.PACKED_SNOW_BRICKS, Blocks.BLUE_ICE))
                    .addProcessor(new RandomizeBlockProcessor(JBlocks.PACKED_SNOW_BRICKS, Blocks.PACKED_ICE));
        }
    }
}