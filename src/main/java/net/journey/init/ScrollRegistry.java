package net.journey.init;

import net.journey.JITL;
import net.journey.api.scroll.ScrollAPI;
import net.journey.api.scroll.ScrollCategory;
import net.journey.api.scroll.ScrollEntry;
import net.journey.api.scroll.ScrollEntryBuilder;
import net.journey.init.items.JourneyItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/*
 * Code by TimeConqueror
 */
public class ScrollRegistry {
	private static final ResourceLocation BASICS = new ResourceLocation(JITL.MOD_ID, "textures/gui/scroll_background.png");

	public static final ScrollEntry SENTERIAN_GOSPEL = new ScrollEntryBuilder("senterian_gospel", "Senterian Gospel", "Book of Divinity", () -> new ItemStack(JourneyItems.sentryKingOrb), 10, 10)
			.addTextComponent("The Senterian Gospel")
			.addTextComponent("We never saw it, but we know it - the Divine land Corba was birthed by the o' powerful Rock and Eye." + "\n" +
					"Never forget the day, the hour, the second he - The True One - the all powerful Rock and Eye -" + "\n" +
					" cometh unto the great world he created, to seek his revenge on the wrong-doers who dare disrespect his land.")
			.build();

	public static void register() {
		ScrollAPI.registerCategory(new ScrollCategory("SENTRY_GOSPEL", BASICS));

		ScrollAPI.registerEntry("SENTRY_GOSPEL", SENTERIAN_GOSPEL);
	}
}
