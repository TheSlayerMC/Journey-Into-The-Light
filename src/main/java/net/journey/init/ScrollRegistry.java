package net.journey.init;

import net.journey.JITL;
import net.journey.api.scroll.*;
import net.journey.init.items.JourneyItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/*
 * Code by TimeConqueror
 */
public class ScrollRegistry {
	private static final ResourceLocation BASICS = new ResourceLocation(JITL.MOD_ID, "textures/gui/scroll_background.png");

	public static ScrollEntry senterianGospel;

	public static void register() {

		ScrollAPI.registerCategory(new ScrollCategory("SENTRY_GOSPEL", BASICS));

		List<IDescComponent> desc = new ArrayList<>();

		desc.add(new StringDescComponent("The Senterian Gospel"));
		desc.add(new StringDescComponent("We never saw it, but we know it - the Divine land Corba was birthed by the o' powerful Rock and Eye." + "\n" +
														"Never forget the day, the hour, the second he - The True One - the all powerful Rock and Eye -" + "\n" +
														" cometh unto the great world he created, to seek his revenge on the wrong-doers who dare disrespect his land"));

		senterianGospel = ScrollAPI.registerEntry("SENTRY_GOSPEL", new ScrollEntry("senterian_gospel_" + 0, "Senterian Gospel", "Book of Divinity", new ItemStack(JourneyItems.sentryKingOrb), desc, 10, 10));
	}
}
