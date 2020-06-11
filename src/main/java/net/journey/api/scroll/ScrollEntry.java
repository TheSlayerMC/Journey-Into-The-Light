package net.journey.api.scroll;

import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/*
 * Code by TimeConqueror
 */
public class ScrollEntry {
	/**
	 * Item that will be displayed on Entry Button in Tablet
	 */
	private final ItemStack displayedItem;
	/**
	 * Id of Entry
	 */
	private final String id;
	/**
	 * Name of Entry
	 */
	private String titleKey;
	/**
	 * Commentary that will be displayed under the title.
	 */
	@Nullable
	private final String commentKey;
	/**
	 * Information that will be displayed when you open selected entry
	 */
	private final List<IDescComponent> desc = new ArrayList<>();
	private final int x;
	private final int y;

	public ScrollEntry(String id, String titleKey, @Nullable String commentKey, ItemStack displayedItem, List<IDescComponent> desc, int x, int y) {
		this.titleKey = titleKey;
		this.displayedItem = displayedItem;
		this.x = x;
		this.y = y;
		this.titleKey = titleKey;
		this.commentKey = commentKey;

		this.desc.add(new UnderHeaderDescComponent());
		this.desc.addAll(desc);

		if (id == null) {
			this.id = this.titleKey.toLowerCase().replace(' ', '_');
		} else {
			this.id = id;
		}
	}

	public ItemStack getDisplayedItem() {
		return displayedItem;
	}

	public String getId() {
		return id;
	}

	public String getTitleKey() {
		return titleKey;
	}

	public List<IDescComponent> getDesc() {
		return desc;
	}

	/**
	 * Adds IDescComponent object to the Description array
	 */
	public void addDescComponent(IDescComponent descComponent) {
		desc.add(descComponent);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public @Nullable String getCommentKey() {
		return commentKey;
	}

	public boolean hasComment() {
		return commentKey != null;
	}

}
