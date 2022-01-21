package net.jitl.core.init.world;

import net.jitl.common.world.gen.structures.processors.RandomizeBlockWithChanceProcessor;
import net.jitl.core.JITL;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import ru.timeconqueror.timecore.api.registry.StructureProcessorTypeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JStructureProcessors {
    @AutoRegistrable
    private static final StructureProcessorTypeRegister REGISTER = new StructureProcessorTypeRegister(JITL.MODID);

    public static final StructureProcessorType<RandomizeBlockWithChanceProcessor> RANDOMIZE_BLOCK_CHANCE_PROCESSOR = REGISTER.register("randomize_block_with_chance", RandomizeBlockWithChanceProcessor.CODEC);
}
