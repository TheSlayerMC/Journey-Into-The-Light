package net.jitl.common.world.gen.structures.overworld;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import net.jitl.JITL;
import net.jitl.init.world.JStructurePieces;
import net.jitl.init.world.JStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import ru.timeconqueror.timecore.api.util.GenHelper;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class AncientRuinsStructure extends StructureFeature<NoneFeatureConfiguration> {

    public static final ResourceLocation STRUCTURE = JITL.rl("overworld/ancient_ruins");

    private static final Map<ResourceLocation, BlockPos> OFFSETS = ImmutableMap.of(
            STRUCTURE, BlockPos.ZERO
    );

    public AncientRuinsStructure(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return Start::new;
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    public static StructurePiece createPiece(StructureManager templateManager, ResourceLocation templateLocation, BlockPos pos, boolean applyGenerationNoise) {
        return new Piece(templateManager, templateLocation, pos);
    }

    public static void generate(List<StructurePiece> pieces, StructureManager templateManager, BlockPos surfacePos) {
        BlockPos changeable = surfacePos;
        pieces.add(createPiece(templateManager, AncientRuinsStructure.STRUCTURE, surfacePos, true));
    }

    public static class Start extends StructureStart<NoneFeatureConfiguration> {
        public Start(StructureFeature<NoneFeatureConfiguration> structure, int chunkX, int chunkZ, BoundingBox mutableBoundingBox_, int references, long seed) {
            super(structure, chunkX, chunkZ, mutableBoundingBox_, references, seed);
        }

        public void generatePieces(RegistryAccess dynamicRegistries, ChunkGenerator chunkGenerator, StructureManager templateManager, int chunkX, int chunkZ, Biome biome_, NoneFeatureConfiguration featureConfig_) {
            int x = chunkX << 4;
            int z = chunkZ << 4;

            int surface = GenHelper.getAverageFirstFreeHeight(chunkGenerator, x, z, x + 10, z + 10);
            surface -= 10;

            BlockPos start = new BlockPos(x, surface, z);
            JITL.LOGGER.debug(JStructures.STRUCTURE_MARKER, "Attempting to generate {} on {}", AncientRuinsStructure.class.getSimpleName(), start);

            generate(pieces, templateManager, start);

            this.calculateBoundingBox();
        }
    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation templateLocation;

        public Piece(StructureManager templateManager, ResourceLocation templateLocation, BlockPos pos) {
            this(JStructurePieces.ANCIENT_RUINS.get(), templateManager, templateLocation, pos);
        }

        public Piece(StructureManager templateManager, CompoundTag nbt) {
            this(JStructurePieces.ANCIENT_RUINS.get(), templateManager, nbt);
        }

        protected Piece(StructurePieceType type, StructureManager templateManager, ResourceLocation templateLocation, BlockPos pos) {
            super(type, 0/*genDepth*/);
            this.templateLocation = templateLocation;
            this.templatePosition = pos.offset(OFFSETS.get(templateLocation));
            loadTemplate(templateManager);
        }

        protected Piece(StructurePieceType type, StructureManager templateManager, CompoundTag nbt) {
            super(type, nbt);
            this.templateLocation = new ResourceLocation(nbt.getString("template"));
            loadTemplate(templateManager);
        }

        @Override
        protected void addAdditionalSaveData(CompoundTag tagCompound) {
            super.addAdditionalSaveData(tagCompound);
            tagCompound.putString("template", this.templateLocation.toString());
        }

        private void loadTemplate(StructureManager templateManager) {
            Random random = new Random();
            StructureTemplate template = templateManager.getOrCreate(this.templateLocation);
            StructurePlaceSettings placementsettings = new StructurePlaceSettings()
                    .setRotation(Rotation.getRandom(random))
                    .setMirror(Mirror.NONE);
//                    .setRotationPivot()
//                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            this.setup(template, this.templatePosition, placementsettings);
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, ServerLevelAccessor worldIn, Random rand, BoundingBox sbb) {

        }
    }
}
