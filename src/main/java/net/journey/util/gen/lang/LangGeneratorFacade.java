package net.journey.util.gen.lang;

import net.journey.JITL;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.EntityEntry;

/**
 * Generates lang entries and places them in en_us.lang.
 * It doesn't delete en_us.lang!
 * If you don't have any special marks in your lang file,
 * it will generate stuff in its end.
 * <p>
 * All public methods here are runtime-safely. While it works in dev workspace, it will be quietly turned off in runtime.
 * <p>
 * If you have stuff to be generated not in the end of the file, you should place
 * <b>#MARK AUTO GEN START</b> and <b>#MARK AUTO GEN END</b> on any two lines of file, where you want to see generated stuff.
 * Be careful, all lines that are between these two marks will be deleted before generating, so don't place custom lang entries there.
 * <p>
 * Example:
 * <blockquote>
 * <pre>
 *  entity.journey.sentrywalker.name=Sentry Walker
 *
 *  #MARK AUTO GEN START
 *  <- (here generator will create all its entries) ->
 *  #MARK AUTO GEN END
 *
 *  advancement.journey.hello=Hello!
 *  </pre>
 * </blockquote>
 *
 * <p>
 * All lang changes will be applied the next launch after being generated.
 */
public class LangGeneratorFacade {
	private static final LangGenerator generator = JITL.IN_JOURNEY_DEV ? new LangGenerator() : null;

	public static void addBlockEntry(Block block, String enName) {
		addLangEntry(LangSection.BLOCKS, block, enName);
	}

	public static void addItemEntry(Item item, String enName) {
		addLangEntry(LangSection.ITEMS, item, enName);
	}

	public static void addEntityEntry(EntityEntry entityEntry, String enName) {
		addLangEntry(LangSection.ENTITIES, entityEntry, enName);
	}

	public static void addCreativeTabEntry(CreativeTabs creativeTab, String enName) {
		addLangEntry(LangSection.CREATIVE_TABS, creativeTab, enName);
	}

	public static void addArmorEntry(Item item, String enName) {
		addLangEntry(LangSection.ARMOR, item, enName);
	}

	public static void addMiscEntry(String key, String name) {
		addLangEntry(LangSection.MISC, key, name);
	}

	public static void save() {
		if (exists()) generator.save();
	}

	private static <T> void addLangEntry(LangSection<T> section, T entry, String enName) {
		if (exists()) generator.addLangEntry(section, entry, enName);
	}

	private static boolean exists() {
		return generator != null;
	}
}
