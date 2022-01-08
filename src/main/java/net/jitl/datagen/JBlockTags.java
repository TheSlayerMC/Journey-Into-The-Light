package net.jitl.datagen;

import net.jitl.JITL;
import net.jitl.init.JBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class JBlockTags extends BlockTagsProvider {

    public JBlockTags(DataGenerator generator_, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator_, JITL.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return JITL.rl("block_tags").toString();
    }

    @Override
    protected void addTags() {
        addJITLBlockTags();
        addVanillaBlockTags();
        addForgeBlockTags();
        addToolTags();
    }

    //TODO: add block tags
    public void addJITLBlockTags() {
    }

    public void addVanillaBlockTags() {
    }

    public void addForgeBlockTags() {
    }

    //TODO: finish tool tags
    public void addToolTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                JBlocks.GOLDITE_STONE,
                JBlocks.EMPTY_BLOOD_RUNE
        );
    }
}
