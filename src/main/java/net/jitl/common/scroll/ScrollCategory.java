package net.jitl.common.scroll;

import net.minecraft.resources.ResourceLocation;

import java.util.LinkedHashMap;

/*
 * Code by TimeConqueror
 */
public class ScrollCategory {

    /**
     * Size of Category. The real size will be multiplied by 256.
     */
    private final int categorySize;
    private final String categoryKey;
    private final ResourceLocation backgroundTexture;
    private final String id;
    /**
     * The list of the entries that are binded to the category
     */
    private final LinkedHashMap<String, ScrollEntry> entryList = new LinkedHashMap<>();

    public ScrollCategory(String categoryKey, /*@NotNull*/ ResourceLocation backgroundTexture) {
        this(categoryKey, backgroundTexture, 2);
    }


    public ScrollCategory(String categoryKey, /*@NotNull*/ ResourceLocation backgroundTexture, int categorySize) {
        this.categorySize = categorySize;
        this.categoryKey = categoryKey;
        this.backgroundTexture = backgroundTexture;
        this.id = this.categoryKey.toUpperCase().replace(' ', '_');
    }

    public int getCategorySize() {
        return categorySize;
    }

    public String getCategoryKey() {
        return categoryKey;
    }

    public LinkedHashMap<String, ScrollEntry> getEntryList() {
        return entryList;
    }

    public void addEntryToCategory(ScrollEntry ScrollEntry) {
        entryList.put(ScrollEntry.getId(), ScrollEntry);
    }

    public ScrollEntry getEntry(String entryID) {
        if (!entryList.containsKey(entryID)) {
            throw new IndexOutOfBoundsException("Attempt to get nonexistent Entry \"" + entryID + "\" in \"" + categoryKey + "\" Category.\n\tAvailable Entry Names: " + entryList.toString());
        }
        return entryList.get(entryID);
    }

    public ResourceLocation getBackgroundTexture() {
        return backgroundTexture;
    }

    public String getId() {
        return id;
    }

    public int getEntryCount() {
        return entryList.size();
    }
}