package net.journey.api.item.scroll;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

/*
 * Code by TimeConqueror
 */
public class ScrollEntry {
    /**
     * Item that will be displayed on Entry Button in Tablet
     */
    private ItemStack displayedItem;
    /**
     * ID of Parent Entry
     */
    private String parendEntryID;
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
    private String comment;
    /**
     * Information that will be displayed when you open selected entry
     */
    private List<IDescComponent> desc = new ArrayList<>();
    private int xCoord;
    private int yCoord;

    public ScrollEntry(String title, ItemStack displayedItem, List<IDescComponent> desc, int xCoord, int yCoord) {
        this(title, null, null, displayedItem, desc, xCoord, yCoord);
    }

    public ScrollEntry(String title, @Nullable String comment, ItemStack displayedItem, List<IDescComponent> desc, int xCoord, int yCoord) {
        this(title, null, comment, displayedItem, desc, xCoord, yCoord);
    }

    public ScrollEntry(String title, @Nullable String id, @Nullable String comment, ItemStack displayedItem, List<IDescComponent> desc, int xCoord, int yCoord) {
        this.title = title;
        this.displayedItem = displayedItem;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.title = I18n.format(title);
        this.comment = comment;

        int i$ = 1;
        this.desc.add(new UnderHeaderDescComponent());
        for (IDescComponent cp : desc) {
            this.desc.add(i$, desc.get(i$ - 1));
            i$ += 1;
        }

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

    public String getParendEntryID() {
        return parendEntryID;
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

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public String getComment() {
        return comment;
    }

    public boolean hasComment() {
        if (comment == null) {
            return false;
        }

        return true;
    }

}
