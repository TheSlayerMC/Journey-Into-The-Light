package net.jitl.common.world.gen.structures.nether;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import net.jitl.core.JITL;
import net.jitl.core.init.world.JStructurePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.QuartPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RangeConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class BoilLockStructure extends StructureFeature<RangeConfiguration> {

    public static final ResourceLocation LOCK = JITL.rl("boil/boil_lock");

    static final Map<ResourceLocation, BlockPos> PIVOTS = ImmutableMap.of(LOCK, new BlockPos(10, 15, 10));
    private static final Map<ResourceLocation, BlockPos> OFFSETS = ImmutableMap.of(
            LOCK, new BlockPos(0, -1, 0)
    );

    public BoilLockStructure(Codec<RangeConfiguration> configCodec_) {
        super(configCodec_, BoilLockStructure::generatePieces);
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.UNDERGROUND_DECORATION;
    }

    private static Optional<PieceGenerator<RangeConfiguration>> generatePieces(PieceGeneratorSupplier.Context<RangeConfiguration> context) {
        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));

        random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);

        int i = context.chunkPos().getMinBlockX() + random.nextInt(16);
        int j = context.chunkPos().getMinBlockZ() + random.nextInt(16);
        int k = context.chunkGenerator().getSeaLevel();

        Rotation rotation = Rotation.getRandom(random);

        WorldGenerationContext worldgenerationcontext = new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor());
        int l = (context.config()).height.sample(random, worldgenerationcontext);

        NoiseColumn noisecolumn = context.chunkGenerator().getBaseColumn(i, j, context.heightAccessor());
        BlockPos.MutableBlockPos mbp = new BlockPos.MutableBlockPos(i, l, j);

        while (l > k) {
            BlockState blockstate = noisecolumn.getBlock(l);
            l--;
            BlockState blockstate1 = noisecolumn.getBlock(l);
            if (blockstate.isAir() && (blockstate1.is(Blocks.SOUL_SAND) || (blockstate1.is(Blocks.NETHERRACK) || blockstate1.isFaceSturdy(EmptyBlockGetter.INSTANCE, mbp.setY(l), Direction.UP)))) {
                break;
            }
        }
        if (l <= k) {
            return Optional.empty();
        } else if (!context.validBiome().test(context.chunkGenerator().getNoiseBiome(QuartPos.fromBlock(i), QuartPos.fromBlock(l), QuartPos.fromBlock(j)))) {
            return Optional.empty();
        } else {
            BlockPos blockpos = new BlockPos(i, l, j);
            return Optional.of((structurePiecesBuilder_, context1_) -> BoilLockStructure.addPieces(context.structureManager(), blockpos, rotation, structurePiecesBuilder_, random));
        }
    }

    public static void addPieces(StructureManager structureManager, BlockPos pos, Rotation rotation, StructurePieceAccessor pieces, Random random) {
        pieces.addPiece(createPiece(structureManager, LOCK, pos, rotation, true));
    }

    public static StructurePiece createPiece(StructureManager templateManager, ResourceLocation templateLocation, BlockPos pos, Rotation rotation, boolean applyGenerationNoise) {
        return new BoilLockStructure.Piece(templateManager, templateLocation, pos, rotation, 0);
    }

    public static class Piece extends TemplateStructurePiece {
        public Piece(StructureManager structureManager_, ResourceLocation location_, BlockPos pos_, Rotation rotation, int down_) {
            super(JStructurePieces.BOIL_LOCK.get(), 0, structureManager_, location_, location_.toString(), makeSettings(rotation, location_), makePosition(location_, pos_, down_));
        }

        public Piece(StructurePieceSerializationContext structurePieceSerializationContext, CompoundTag compoundTag) {
            super(JStructurePieces.BOIL_LOCK.get(), compoundTag, structurePieceSerializationContext.structureManager(), (resourceLocation_) -> makeSettings(Rotation.valueOf(compoundTag.getString("Rot")), resourceLocation_));
        }

        private static StructurePlaceSettings makeSettings(Rotation rotation_, ResourceLocation location_) {
            return (new StructurePlaceSettings())
                    .setRotation(rotation_)
                    .setMirror(Mirror.NONE)
                    .setRotationPivot(BoilLockStructure.PIVOTS.get(location_));
        }

        private static BlockPos makePosition(ResourceLocation location_, BlockPos pos_, int down_) {
            return pos_.offset(BoilLockStructure.OFFSETS.get(location_)).below(down_);
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
