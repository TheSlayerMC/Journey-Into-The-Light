package net.jitl.core.data;

import net.jitl.core.JITL;
import net.jitl.core.init.JBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JBlockTags extends BlockTagsProvider {

    public JBlockTags(DataGenerator generator_, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator_, JITL.MODID, existingFileHelper);
    }

    @Override
    public @NotNull String getName() {
        return JITL.rl("block_tags").toString();
    }

    @Override
    protected void addTags() {
        addJITLBlockTags();
        addVanillaBlockTags();
        addForgeBlockTags();
        addToolTags();
        addRequiredToolTags();
    }

    //TODO: add block tags
    private void addJITLBlockTags() {
    }

    private void addVanillaBlockTags() {
    }

    private void addForgeBlockTags() {
    }

    //TODO: finish tool tags
    private void addToolTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                JBlocks.LUNIUM_ORE,
                JBlocks.DEEPSLATE_LUNIUM_ORE,
                JBlocks.SHADIUM_ORE,
                JBlocks.DEEPSLATE_SHADIUM_ORE,
                JBlocks.SAPPHIRE_ORE,
                JBlocks.DEEPSLATE_SAPPHIRE_ORE,
                JBlocks.IRIDIUM_ORE,
                JBlocks.DEEPSLATE_IRIDIUM_ORE,
                JBlocks.GOLDITE_STONE,
                JBlocks.EMPTY_BLOOD_RUNE,
                JBlocks.DEEP_MYCELIUM,
                JBlocks.BLOODCRUST_ORE,
                JBlocks.BLAZIUM_ORE,
                JBlocks.STORON_ORE,
                JBlocks.MEKYUM_ORE,
                JBlocks.KORITE_ORE,
                JBlocks.CELESTIUM_ORE,
                JBlocks.RIMESTONE_ORE,
                JBlocks.PERIDOT_ORE,
                JBlocks.PERMAFROST,
                JBlocks.ICICLE
        );
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                JBlocks.CRUMBLED_PERMAFROST,
                JBlocks.GRASSY_PERMAFROST
        );
        tag(BlockTags.MINEABLE_WITH_AXE).add(
                JBlocks.FROZEN_LOG
        );
    }

    //TODO: finish required tool tags
    private void addRequiredToolTags() {
        tag(Tags.Blocks.NEEDS_WOOD_TOOL).add(
                JBlocks.DEEP_MYCELIUM,
                JBlocks.ICICLE
        );
        tag(BlockTags.NEEDS_STONE_TOOL).add(
                JBlocks.PERMAFROST
        );
        tag(BlockTags.NEEDS_IRON_TOOL).add(
                JBlocks.SAPPHIRE_ORE,
                JBlocks.DEEPSLATE_SAPPHIRE_ORE,
                JBlocks.SHADIUM_ORE,
                JBlocks.DEEPSLATE_SHADIUM_ORE,
                JBlocks.LUNIUM_ORE,
                JBlocks.DEEPSLATE_LUNIUM_ORE,
                JBlocks.IRIDIUM_ORE,
                JBlocks.DEEPSLATE_IRIDIUM_ORE,
                JBlocks.DREADIRON_BLOCK,
                JBlocks.RIMESTONE_ORE,
                JBlocks.PERIDOT_ORE
        );
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(
                JBlocks.BLAZIUM_ORE,
                JBlocks.STORON_ORE,
                JBlocks.MEKYUM_ORE,
                JBlocks.KORITE_ORE,
                JBlocks.CELESTIUM_ORE
        );
    }
}
