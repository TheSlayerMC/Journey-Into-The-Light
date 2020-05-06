package net.journey.util.gen.lang;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.EntityEntry;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.function.Function;

public class LangSection<T> {
	static final ArrayList<LangSection<?>> SECTIONS = new ArrayList<>();

	public static final LangSection<CreativeTabs> CREATIVE_TABS = new LangSection<>("CreativeTabs", CreativeTabs::getTranslationKey);
	public static final LangSection<Block> BLOCKS = new LangSection<>("Blocks", block -> block.getTranslationKey() + ".name");
	public static final LangSection<Item> ITEMS = new LangSection<>("Items", item -> item.getTranslationKey() + ".name");
	public static final LangSection<Item> ARMOR = new LangSection<>("Armor", item -> item.getTranslationKey() + ".name");
	public static final LangSection<EntityEntry> ENTITIES = new LangSection<>("Entities", s -> "entity." + s.getName() + ".name");
	public static final LangSection<String> MISC = new LangSection<>("Miscellaneous", s -> s);

	private String name;
	private Function<T, String> keyRetriever;

	public LangSection(String name, @NotNull Function<T, String> keyRetriever) {
		this.name = name;
		this.keyRetriever = keyRetriever;

		SECTIONS.add(this);
	}

	public String getComment() {
		return "#" + name;
	}

	public String retrieveKey(T entry) {
		return keyRetriever.apply(entry);
	}

	public String createLangEntry(T entry, String enName) {
		return retrieveKey(entry) + "=" + enName;
	}
}
