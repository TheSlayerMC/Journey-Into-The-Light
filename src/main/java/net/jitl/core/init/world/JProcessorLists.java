package net.jitl.core.init.world;

import com.google.common.collect.ImmutableList;
import net.jitl.core.JITL;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

public class JProcessorLists {

    private static final ProcessorRule REPLACE_AIR_WITH_STRUCTURE_VOID = new ProcessorRule(
            new RandomBlockMatchTest(Blocks.AIR, 1.0F),
            AlwaysTrueTest.INSTANCE,
            Blocks.STRUCTURE_VOID.defaultBlockState()); //this is a test

    public static final StructureProcessorList IGLOO_PROCESSOR = register(
            "igloo_processor",
            ImmutableList.of(
                    new RuleProcessor(ImmutableList.of(REPLACE_AIR_WITH_STRUCTURE_VOID))));

    private static StructureProcessorList register(String string_, ImmutableList<StructureProcessor> immutableList_) {
        ResourceLocation resourcelocation = JITL.rl(string_);
        StructureProcessorList structureprocessorlist = new StructureProcessorList(immutableList_);
        return BuiltinRegistries.register(BuiltinRegistries.PROCESSOR_LIST, resourcelocation, structureprocessorlist);
    }
}
