package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.world.gen.structures.processors.RandomizeBlockWithChanceProcessor;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import ru.timeconqueror.timecore.api.registry.StructureProcessorTypeRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JStructureProcessors {
    @AutoRegistrable
    private static final StructureProcessorTypeRegister REGISTER = new StructureProcessorTypeRegister(JITL.MODID);

    //TODO: create custom processor for illager bunker
    public static final IStructureProcessorType<RandomizeBlockWithChanceProcessor> RANDOMIZE_BLOCK_CHANCE_PROCESSOR = REGISTER.register("randomize_block_with_chance", RandomizeBlockWithChanceProcessor.CODEC);
}
