package net.jitl.common.world.gen.structures.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.init.TStructureProcessorTypes;
import ru.timeconqueror.timecore.api.util.ExtraCodecs;
import ru.timeconqueror.timecore.api.util.MathUtils;
import ru.timeconqueror.timecore.api.util.RandHelper;

public class RandomizeBlockWithChanceProcessor extends StructureProcessor {
    public static final Codec<RandomizeBlockWithChanceProcessor> CODEC = RecordCodecBuilder.create(instance ->
            instance
                    .group(ExtraCodecs.BLOCK.fieldOf("to_replace").forGetter(p -> p.toReplace),
                            ExtraCodecs.BLOCK.fieldOf("by").forGetter(p -> p.replacement),
                            Codec.INT.fieldOf("chance").forGetter(p -> p.chance))
                    .apply(instance, RandomizeBlockWithChanceProcessor::new)
    );

    private final Block toReplace;
    private final Block replacement;
    private final int chance;

    public RandomizeBlockWithChanceProcessor(Block toReplace, Block replacement) {
        this(toReplace, replacement, 10);
    }

    public RandomizeBlockWithChanceProcessor(Block toReplace, Block replacement, int chance) {
        this.toReplace = toReplace;
        this.replacement = replacement;
        this.chance = MathUtils.coerceInRange(chance, 0, 100);
    }

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo process(LevelReader world, BlockPos templatePosition, BlockPos pieceBottomCenter, StructureTemplate.StructureBlockInfo original, StructureTemplate.StructureBlockInfo modified, StructurePlaceSettings settings, @Nullable StructureTemplate template) {
        if (modified.state.getBlock() == toReplace && RandHelper.chance(settings.getRandom(modified.pos), chance)) {
            return new StructureTemplate.StructureBlockInfo(modified.pos, replacement.defaultBlockState(), modified.nbt);
        }

        return modified;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return TStructureProcessorTypes.RANDOMIZE_BLOCK_PROCESSOR;
    }
}