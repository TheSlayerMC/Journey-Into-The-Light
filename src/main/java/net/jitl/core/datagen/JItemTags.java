package net.jitl.core.datagen;

import net.jitl.core.JITL;
import net.jitl.core.init.JItems;
import net.jitl.core.init.JTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class JItemTags extends ItemTagsProvider {
    public JItemTags(DataGenerator generator_, BlockTagsProvider blockTagsProvider_, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator_, blockTagsProvider_, JITL.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return JITL.rl("item_tags").toString();
    }

    @Override
    protected void addTags() {
        addJITLItemTags();
        addVanillaItemTags();
        addForgeItemTags();
    }

    public void addJITLItemTags() {
        tag(JTags.FROZEN_TROLL_LOVED_ITEMS).add(
                Items.DIAMOND,
                Items.EMERALD,
                JItems.RIMESTONE,
                JItems.PERIDOT_GEMSTONE
        );
    }

    public void addVanillaItemTags() {
    }

    public void addForgeItemTags() {
    }
}
