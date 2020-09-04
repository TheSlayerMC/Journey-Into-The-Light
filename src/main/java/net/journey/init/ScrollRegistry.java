package net.journey.init;

import net.journey.JITL;
import net.journey.api.scroll.ScrollAPI;
import net.journey.api.scroll.ScrollCategory;
import net.journey.api.scroll.ScrollEntry;
import net.journey.api.scroll.ScrollEntryBuilder;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/*
 * Code by TimeConqueror
 */
public class ScrollRegistry {
	private static final ResourceLocation BG = JITL.rl("textures/gui/scroll_background.png");

	public static final ScrollEntry SENTERIAN_GOSPEL = new ScrollEntryBuilder("senterian_gospel", "Senterian Gospel", "Book of Divinity", () -> new ItemStack(JourneyItems.sentryKingOrb), 10, 10)
			.addTextComponent("scroll.journey.sentry_gospel")
			.build();

	public static final ScrollEntry MY_LAST_WORDS = new ScrollEntryBuilder("my_last_words", "My Last Words", "They're Coming...", () -> new ItemStack(JourneyBlocks.ancientMachineBlock), 10, 10)
			.addTextComponent("scroll.journey.chap1.my_last_words")
			.build();

	public static void register() {
		ScrollAPI.registerCategory(new ScrollCategory("SENTRY_GOSPEL", BG));
		ScrollAPI.registerEntry("SENTRY_GOSPEL", SENTERIAN_GOSPEL);

		ScrollAPI.registerCategory(new ScrollCategory("MY_LAST_WORDS", BG));
		ScrollAPI.registerEntry("MY_LAST_WORDS", MY_LAST_WORDS);
	}
}
