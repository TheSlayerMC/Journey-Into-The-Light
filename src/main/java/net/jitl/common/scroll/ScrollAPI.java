package net.jitl.common.scroll;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Code by TimeConqueror
 */
public class ScrollAPI {
    private static final Map<String, ScrollCategory> CATEGORY_MAP = new LinkedHashMap<>();
    private static final Map<String, ScrollEntry> ENTRY_MAP = new HashMap<>();

    public static void registerCategory(String categoryName, ResourceLocation background) {
        registerCategory(categoryName, background, 2);
    }

    public static void registerCategory(String categoryName, ResourceLocation background, int categorySize) {
        ScrollCategory ScrollCategory = new ScrollCategory(categoryName, background, categorySize);
        registerCategory(ScrollCategory);
    }

    public static void registerCategory(ScrollCategory scrollCategory) {
        CATEGORY_MAP.put(scrollCategory.getCategoryKey(), scrollCategory);
    }

    public static ScrollEntry registerEntry(String existentCategoryName, ScrollEntry scrollEntry) {
        if (existentCategoryName == null || !CATEGORY_MAP.containsKey(existentCategoryName)) {
            throw new IndexOutOfBoundsException("Attempt to register Scroll Entry \"" + scrollEntry.getId() + "\" to nonexistent Category \"" + existentCategoryName + "\".\n\tAvailable Category Names: " + CATEGORY_MAP.toString());
        }

        CATEGORY_MAP.get(existentCategoryName).addEntryToCategory(scrollEntry);

        if (ENTRY_MAP.put(scrollEntry.getId(), scrollEntry) != null) {
            throw new IllegalStateException("Scroll Entry with id " + scrollEntry.getId() + " already exists!");
        }

        return scrollEntry;
    }

    public static Map<String, ScrollCategory> getCategoryMap() {
        return Collections.unmodifiableMap(CATEGORY_MAP);
    }

    public static Map<String, ScrollEntry> getEntryMap() {
        return Collections.unmodifiableMap(ENTRY_MAP);
    }

    @Nullable
    public static ScrollEntry getEntry(String id) {
        return ENTRY_MAP.get(id);
    }

    public static ScrollCategory getCategoryByName(String categoryName) {
        categoryName = categoryName.toUpperCase();
        if (!CATEGORY_MAP.containsKey(categoryName)) {
            throw new IndexOutOfBoundsException("Attempt to get the nonexistent Category \"" + categoryName + "\".\n\tAvailable Category Names: " + CATEGORY_MAP.toString());
        }
        return CATEGORY_MAP.get(categoryName);
    }

    public static ScrollCategory getFirstCategory() {
        Map.Entry<String, ScrollCategory> entry = CATEGORY_MAP.entrySet().iterator().next();
        return entry.getValue();
    }

    public static ScrollCategory getCategoryByIndex(int index) {
        Map.Entry<String, ScrollCategory> category = CATEGORY_MAP.entrySet().stream().skip(index).findFirst().get();
        return category.getValue();
    }

    public static String getCategoryIdByIndex(int index) {
        Map.Entry<String, ScrollCategory> category = CATEGORY_MAP.entrySet().stream().skip(index).findFirst().get();
        return category.getKey();
    }

    public static ScrollEntry getEntryByIndex(ScrollCategory category, int index) {
        Map.Entry<String, ScrollEntry> entry = category.getEntryList().entrySet().stream().skip(index).findFirst().get();
        return entry.getValue();
    }

    public static int getIndexOfCategory(ScrollCategory category) {
        for (int i = 0; i < CATEGORY_MAP.size(); i++) {
            if (category.equals(CATEGORY_MAP.entrySet().stream().skip(i).findFirst().get().getValue())) {
                return i;
            }
        }
        return -1;
    }
}