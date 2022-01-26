package net.jitl.core.init.client;

import net.jitl.common.scroll.ScrollAPI;
import net.jitl.common.scroll.ScrollCategory;
import net.jitl.common.scroll.ScrollEntry;
import net.jitl.common.scroll.ScrollEntryBuilder;
import net.jitl.core.JITL;
import net.jitl.core.init.JBlocks;
import net.jitl.core.init.JItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ScrollEntries {
    private static final ResourceLocation BG = JITL.rl("textures/gui/scroll_background.png");

    public static final ScrollEntry SENTERIAN_GOSPEL = new ScrollEntryBuilder("senterian_gospel", "Senterian Gospel", "Book of Divinity", () -> new ItemStack(JItems.LOOT_POUCH_GOLD), 10, 10)
            .addTextComponent("scroll.jitl.sentry_gospel")
            .build();

    public static final ScrollEntry MY_LAST_WORDS = new ScrollEntryBuilder("my_last_words", "My Last Words", "They're Coming...", () -> new ItemStack(JBlocks.GOLDITE_STONE), 10, 10)
            .addTextComponent("scroll.jitl.chap1.my_last_words")
            .build();

    public static void register() {
        ScrollAPI.registerCategory(new ScrollCategory("SENTRY_GOSPEL", BG));
        ScrollAPI.registerEntry("SENTRY_GOSPEL", SENTERIAN_GOSPEL);

        ScrollAPI.registerCategory(new ScrollCategory("MY_LAST_WORDS", BG));
        ScrollAPI.registerEntry("MY_LAST_WORDS", MY_LAST_WORDS);
    }
}
