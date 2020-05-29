package net.journey.api.scroll;

import net.minecraft.client.resources.I18n;
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
	private ItemStack displayedItem;
	/**
	 * Id of Entry
	 */
	private String id;
	/**
	 * Name of Entry
	 */
	private String title;
	/**
	 * Commentary that will be displayed under the title
	 */
	@Nullable
	private String comment;
	/**
	 * Information that will be displayed when you open selected entry
	 */
	private List<IDescComponent> desc = new ArrayList<>();
	private int x;
	private int y;

	public ScrollEntry(String id, String title, @Nullable String comment, ItemStack displayedItem, List<IDescComponent> desc, int x, int y) {
		this.title = title;
		this.displayedItem = displayedItem;
		this.x = x;
		this.y = y;
		this.title = I18n.format(title);
		this.comment = comment;

		this.desc.add(new UnderHeaderDescComponent());
		this.desc.addAll(desc);

		if (id == null) {
			this.id = this.title.toLowerCase().replace(' ', '_');
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

	public String getTitle() {
		return title;
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

	public String getComment() {
		return comment;
	}

	public boolean hasComment() {
		return comment != null;
	}

}
