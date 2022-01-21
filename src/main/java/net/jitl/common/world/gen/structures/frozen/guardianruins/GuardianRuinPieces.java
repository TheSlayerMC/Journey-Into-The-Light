package net.jitl.common.world.gen.structures.frozen.guardianruins;

public class GuardianRuinPieces {
    /*public static final int BB_WIDTH = 20;
    public static final ResourceLocation RUINS = JITL.rl("frozen/guardian_ruin");
    private static final Map<ResourceLocation, BlockPos> OFFSETS = ImmutableMap.of(
            RUINS, new BlockPos(0, -1, 0)
    );

    public static void generate(List<StructurePiece> pieces, StructureManager templateManager, BlockPos surfacePos) {
        pieces.add(createPiece(templateManager, RUINS, surfacePos, true));
    }

    public static StructurePiece createPiece(StructureManager templateManager, ResourceLocation templateLocation, BlockPos pos, boolean applyGenerationNoise) {
        return new Piece(templateManager, templateLocation, pos);
    }

    public static class Piece extends TunedTemplateStructurePiece {
        public Piece(StructureManager templateManager, ResourceLocation templateLocation, BlockPos pos) {
            this(JStructurePieces.GUARDIAN_RUIN.get(), templateManager, templateLocation, pos);
        }

        public Piece(StructureManager templateManager, CompoundTag nbt) {
            this(JStructurePieces.GUARDIAN_RUIN.get(), templateManager, nbt);
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
            return new StructurePlaceSettings()
                    .addProcessor(new RandomizeBlockProcessor(JBlocks.PACKED_ICE_BRICKS, Blocks.ICE, 50))
                    .addProcessor(new RandomizeBlockProcessor(JBlocks.PACKED_ICE_BRICKS, Blocks.PACKED_ICE, 50))
                    .addProcessor(new RandomizeBlockProcessor(Blocks.BLUE_ICE, Blocks.PACKED_ICE, 50))
                    .addProcessor(new RandomizeBlockProcessor(Blocks.BLUE_ICE, Blocks.ICE, 50));
        }
    }*/
}
