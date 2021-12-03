package net.jitl.init;

import com.google.common.collect.ImmutableList;
import net.jitl.JITL;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.template.*;

public class JProcessorLists {

    private static final RuleEntry REPLACE_AIR_WITH_STRUCTURE_VOID = new RuleEntry(
            new RandomBlockMatchRuleTest(Blocks.AIR, 1.0F),
            AlwaysTrueRuleTest.INSTANCE,
            Blocks.STRUCTURE_VOID.defaultBlockState()); //this is a test

    public static final StructureProcessorList IGLOO_PROCESSOR = register(
            "igloo_processor",
            ImmutableList.of(
                    new RuleStructureProcessor(ImmutableList.of(REPLACE_AIR_WITH_STRUCTURE_VOID))));

    private static StructureProcessorList register(String string_, ImmutableList<StructureProcessor> immutableList_) {
        ResourceLocation resourcelocation = JITL.rl(string_);
        StructureProcessorList structureprocessorlist = new StructureProcessorList(immutableList_);
        return WorldGenRegistries.register(WorldGenRegistries.PROCESSOR_LIST, resourcelocation, structureprocessorlist);
    }
}
