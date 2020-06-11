package net.journey.api.scroll;

import net.journey.util.Lazy;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ScrollEntryBuilder {
	private final String id;
	private final String titleKey;
	private final String commentKey;
	private final Lazy<ItemStack> displayedItem;
	private final List<IDescComponent> pageContent = new ArrayList<>();
	private final int x;
	private final int y;

	public ScrollEntryBuilder(String id, String titleKey, String commentKey, Lazy<ItemStack> displayedItem, int x, int y) {
		this.id = id;
		this.titleKey = titleKey;
		this.commentKey = commentKey;
		this.displayedItem = displayedItem;
		this.x = x;
		this.y = y;
	}

	public ScrollEntryBuilder addComponent(IDescComponent component) {
		pageContent.add(component);
		return this;
	}

	public ScrollEntryBuilder addTextComponent(String langKey) {
		pageContent.add(new TextDescComponent(langKey));
		return this;
	}

	public ScrollEntry build() {
		return new ScrollEntry(id, titleKey, commentKey, displayedItem, pageContent, x, y);
	}
}
